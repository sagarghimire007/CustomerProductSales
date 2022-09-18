package com.spring.customerproductsales.services.impl;

import com.spring.customerproductsales.model.Category;
import com.spring.customerproductsales.repository.CategoryRepository;
import com.spring.customerproductsales.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllOrderByCategoryName() {
        return categoryRepository.findAllOrderByCategoryName();
    }

    @Override
    public Category save(Category category) {
        try{
            Category categorySave = new Category(category.getCategoryName());
            return categoryRepository.save(categorySave);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category getById(Integer id) {
        return categoryRepository.getReferenceById(id);
    }

    @Override
    public Category update(Category category) {

        Category categoryUpdate = null;
        try {
            categoryUpdate = categoryRepository.getById(category.getId());
            categoryUpdate.setCategoryName(category.getCategoryName());
            categoryUpdate.setIsActivated(category.isActivated());
            categoryUpdate.setIsDeleted(category.isDeleted());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryRepository.save(categoryUpdate);
    }

    @Override
    public void deleteById(Integer id) {
        Category category = categoryRepository.getById(id);
        category.setIsDeleted(true);
        category.setIsActivated(false);
        categoryRepository.save(category);
    }

    @Override
    public void enabledById(Integer id) {
        Category category = categoryRepository.getById(id);
        category.setIsDeleted(false);
        category.setIsActivated(true);
        categoryRepository.save(category);
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.getById(id);
    }

    @Override
    public List<Category> findAllByActivated() {
        return categoryRepository.findAllByActivated();
    }


}
