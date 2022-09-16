package com.spring.customerproductsales.controller;

import com.spring.customerproductsales.model.Category;
import com.spring.customerproductsales.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String categories(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }

        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoriesList", categoryList);
        model.addAttribute("size", categoryList.size());
        model.addAttribute("title", "Category");
        model.addAttribute("categoryNew", new Category());
        return "categories";
    }

    @GetMapping("/add-category")
    public String addCategory(Model model){
        model.addAttribute("categoryNew", new Category());
        model.addAttribute("title","Add Category");
        return "add-category";

    }

    @PostMapping("/add-category")
    public String addCategory(@ModelAttribute("categoryNew") Category category, RedirectAttributes attributes){

           String categoryName = category.getCategoryName();
           Category categoryfind = categoryService.findByCategoryName(categoryName);

           if(category.getCategoryName() == ""){
               attributes.addFlashAttribute("failed",  "Category Cannot be empty");
           }else if(categoryfind == null) {
               categoryService.save(category);
               attributes.addFlashAttribute("success" , " Category Added Successfully");
           } else{
               attributes.addFlashAttribute("failed",  "Category Already Exist");
           }

       return "redirect:/categories";

    }

    @RequestMapping("/edit-category")
    public String editCategoryById(@RequestParam Integer id, Model model){
        model.addAttribute("title", "Edit Category");
        model.addAttribute("categoryNew", categoryService.findById(id));
        return "edit-category";
    }


    @PostMapping("/update-category")
    public String update(@ModelAttribute("categoryNew") Category category, RedirectAttributes redirectAttributes) {

        Category categoryFindName = categoryService.getById(category.getId());
        String categoryName = categoryFindName.getCategoryName();

            if(category.getCategoryName() == ""){
                redirectAttributes.addFlashAttribute("failed", "Enter Category Name!!");
                return "redirect:/categories";
            } else if(category.getCategoryName().equals(categoryName)){
                redirectAttributes.addFlashAttribute("failed", "Duplicate Category Name won't exist !!");
                return "redirect:/categories";
            }else {

                categoryService.update(category);
                redirectAttributes.addFlashAttribute("success" , "Category Updated Successfully !!");
            }
        return "redirect:/categories";
    }

    @GetMapping("/delete-category")
    public String deleteCategory(@RequestParam Integer id){

        categoryService.deleteById(id);
        return "redirect:/categories";
    }

    @GetMapping("/enable-category")
    public String enableCategory(@RequestParam Integer id){

        categoryService.enabledById(id);
        return "redirect:/categories";
    }
}
