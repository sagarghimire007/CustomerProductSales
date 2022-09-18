package com.spring.customerproductsales.services;

import com.spring.customerproductsales.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllOrderByCategoryName();

    Category save(Category category);

    Category getById(Integer id);

    Category update(Category category);

    void deleteById(Integer id);

    void enabledById(Integer id);

    Category findByCategoryName(String categoryName);

    Category findById(Integer id);

    List<Category> findAllByActivated();
}

