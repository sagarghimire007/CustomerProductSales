package com.spring.customerproductsales.services.impl;

import com.spring.customerproductsales.dto.ProductDto;
import com.spring.customerproductsales.model.Product;
import com.spring.customerproductsales.repository.ProductRepository;
import com.spring.customerproductsales.services.ProductService;
import com.spring.customerproductsales.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageUpload imageUpload;


    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for(Product product : products){
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setProductName(product.getProductName());
            productDto.setDescription(product.getDescription());
            productDto.setCostPrice(product.getCostPrice());
            productDto.setSalesPrice(product.getSalesPrice());
            productDto.setCurrentQuantity(product.getCurrentQuantity());
            productDto.setCategory(product.getCategory());
//            productDto.setImage(product.getImage());

            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public Product save(MultipartFile imageProduct,ProductDto productDto) {
        Product product = new Product();
        try{
            if(imageProduct.isEmpty()){
                System.out.println("File is empty");
                product.setImage(null);
            }else{

                // processing and uploading file
//                productDto.setImage(imageProduct.getOriginalFilename());

                // declare path
//                File saveFile = new ClassPathResource("/static/img/product-image").getFile();
//
//                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+imageProduct.getOriginalFilename());
//
//                Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                imageUpload.uploadImage((imageProduct));
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            product.setImage("");
            product.setProductName(productDto.getProductName());
            product.setDescription(productDto.getDescription());
            product.setCategory(productDto.getCategory());
            product.setCostPrice(productDto.getCostPrice());
            product.setCostPrice(productDto.getSalesPrice());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.setProductCompanyName(productDto.getProductCompanyName());

            return productRepository.save(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product update(ProductDto productDto) {
        try {
            Product product = productRepository.getById(productDto.getId());
            // image check
            product.setProductName(productDto.getProductName());
            product.setDescription(productDto.getDescription());
            product.setCategory(productDto.getCategory());
            product.setCostPrice(productDto.getCostPrice());
            product.setCostPrice(productDto.getSalesPrice());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.setProductCompanyName(productDto.getProductCompanyName());
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void enableById(Integer id) {

    }

    @Override
    public List<Product> findAllByActivated() {
        return productRepository.findAllByActivated();
    }

    @Override
    public ProductDto findById(Integer id) {
        Product product = productRepository.getById(id);
        ProductDto productDto = new ProductDto();

        productDto.setProductName(product.getProductName());
        productDto.setDescription(product.getDescription());
        productDto.setCurrentQuantity(product.getCurrentQuantity());
        productDto.setCostPrice(product.getCostPrice());
        productDto.setSalesPrice(product.getSalesPrice());
        productDto.setProductCompanyName(product.getProductCompanyName());
        productDto.setActivated(productDto.isActivated());
        productDto.setDeleted(productDto.isDeleted());
        return productDto;
    }
}
