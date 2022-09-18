package com.spring.customerproductsales.repository;

import com.spring.customerproductsales.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

   @Query("select p from Product p where p.isActivated = true and p.isDeleted = false")
    List<Product> findAllByActivated();
}
