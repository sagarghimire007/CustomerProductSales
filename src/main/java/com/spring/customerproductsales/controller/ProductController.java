package com.spring.customerproductsales.controller;

import com.spring.customerproductsales.dto.ProductDto;
import com.spring.customerproductsales.model.Category;
import com.spring.customerproductsales.model.Product;
import com.spring.customerproductsales.services.CategoryService;
import com.spring.customerproductsales.services.ProductService;
import com.spring.customerproductsales.services.impl.CategoryServiceImpl;
import com.spring.customerproductsales.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.model.IModel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@Controller
public class ProductController {

    public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/img/product-image";

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductServiceImpl productServices;

    @GetMapping("/products")
    public String products(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }

        List<ProductDto> productListDto = productServices.findAll();
        model.addAttribute("title", "Manage Product");
        model.addAttribute("productDto", productListDto);
        model.addAttribute("size", productListDto.size());
        return "products";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }

        List<Category> categories = categoryService.findAllByActivated();
        model.addAttribute("categories", categories);
        model.addAttribute("productDto", new ProductDto());
        return "add-product";
    }

//    @PostMapping("/save-product")
//    public String saveProduct(@ModelAttribute("productDto") ProductDto productDto, @RequestParam("image") MultipartFile imageProduct, RedirectAttributes redirectAttributes){
//
//        try{
//            productServices.save(imageProduct,productDto);
//            redirectAttributes.addFlashAttribute("success", "Added Successfully");
//        } catch (Exception e) {
//            e.printStackTrace();
//            redirectAttributes.addFlashAttribute("failed", "Failed to add Product !!");
//        }
//        return "redirect:/products";
//    }


    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("productDto") ProductDto productDto, @RequestParam("image")MultipartFile imageProduct, RedirectAttributes redirectAttributes){

        Product product = new Product();
        try{
            product.setProductName(productDto.getProductName());
            product.setDescription(productDto.getDescription());
            product.setProductCompanyName(productDto.getProductCompanyName());
            product.setCategory(productDto.getCategory());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.setCostPrice(productDto.getCostPrice());
            product.setSalesPrice(productDto.getSalesPrice());

            String imgProduct = new String(imageProduct.getBytes());

            String image;
            if(!imgProduct.isEmpty()){
                image = Base64.getEncoder().encodeToString(imageProduct.getBytes()); // get original file name
                Path imagePathAndName = Paths.get(uploadDir,  image);
                Files.write(imagePathAndName, image.getBytes());
            }else{
                image = "";
            }
            product.setImage(image);
            productServices.save(product);
            redirectAttributes.addFlashAttribute("success", "Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Failed to add Product !!");
        }
        return "redirect:/products";
    }


    @GetMapping("/edit-product")
    public String updateProduct(@RequestParam Integer id, Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        model.addAttribute("title", "Update Product");
        List<Category> categories = categoryService.findAllByActivated();
        ProductDto productDto = productService.findById(id);
        model.addAttribute("categories", categories);
        model.addAttribute("product", productDto);
        return "edit-product";
    }

    @PostMapping("/update-product")
    public String updateProduct(@RequestParam Integer id,@ModelAttribute("productDto") ProductDto productDto , RedirectAttributes redirectAttributes){

        Product product = productService.update(productDto);
        if(product != null) {
            redirectAttributes.addFlashAttribute("success" , "Update Successfully");
        }else{
            redirectAttributes.addFlashAttribute("failed", "Failed to Update Product");
        }
        return null;
    }

    @GetMapping("/delete-product")
    public String deleteProduct(@RequestParam Integer id){
        productServices.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/enable-product")
    public String enableProduct(@RequestParam Integer id){
        productServices.enableById(id);
        return "redirect:/products";
    }
}
