/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.model;

/**
 *
 * @author Admin
 */
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private int idProduct;
    private String name;
    private int categoryId;
    private int brandId;
    private BigDecimal price;
    private int stock;
    private String description;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // Default constructor
    public Product() {
        this.status = "Active";
        this.stock = 0;
        this.createDate = LocalDateTime.now();
    }

    // Parameterized constructor
    public Product(int idProduct, String name, int categoryId, int brandId, BigDecimal price, 
                  int stock, String description, String status, LocalDateTime createDate, 
                  LocalDateTime updateDate) {
        this.idProduct = idProduct;
        this.name = name;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    // Minimal constructor with required fields
    public Product(String name, int categoryId, int brandId, BigDecimal price) {
        this.name = name;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.price = price;
        this.status = "Active";
        this.stock = 0;
        this.createDate = LocalDateTime.now();
    }

    // Getters and Setters
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
