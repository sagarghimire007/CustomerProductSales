package com.spring.customerproductsales.services;

import com.spring.customerproductsales.dto.ProductDto;
import com.spring.customerproductsales.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll();

    Product save(MultipartFile imageProduct, ProductDto productDto);

//    Product save(ProductDto productDto);

    Product update(ProductDto productDto);

    void deleteById(Integer id);

    void enableById(Integer id);

    List<Product> findAllByActivated();

    ProductDto findById(Integer id);
}
