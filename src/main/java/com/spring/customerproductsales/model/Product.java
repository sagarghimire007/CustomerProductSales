package com.spring.customerproductsales.model;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_product" , indexes = {@Index(name = "product_name_UNIQUE", columnList = "product_name", unique = true)})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "product_name", nullable = false, length = 30)
    private String productName;

    @Column(name = "product_description")
    private String description;

    @Column(name = "cost_price")
    private double costPrice;

    @Column(name = "sales_price")
    private double salesPrice;

    @Column(name = "current_quantity")
    private int currentQuantity;

    @Column(name = "product_image")
    private String image;

    @Column(name = "product_company_name", length = 30)
    private String productCompanyName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @Column(name = "is_activated")
    private boolean isActivated;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public Product() {
    }

    public Product(String productName , String description , double costPrice , double salesPrice , int currentQuantity , String image , String productCompanyName , Category category) {
        this.productName = productName;
        this.description = description;
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
        this.currentQuantity = currentQuantity;
        this.image = image;
        this.productCompanyName = productCompanyName;
        this.category = category;
        this.isActivated = true;
        this.isDeleted = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductCompanyName() {
        return productCompanyName;
    }

    public void setProductCompanyName(String productCompanyName) {
        this.productCompanyName = productCompanyName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}