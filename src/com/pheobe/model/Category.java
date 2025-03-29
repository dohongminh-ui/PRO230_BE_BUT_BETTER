/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.model;

/**
 *
 * @author Admin
 */
import java.time.LocalDateTime;

public class Category {
    private int categoryId;
    private String name;
    private String description;
    private String status;
    private Integer parentCategoryId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // Default constructor
    public Category() {
        this.status = "Active";
        this.createDate = LocalDateTime.now();
    }

    // Parameterized constructor
    public Category(int categoryId, String name, String description, String status, 
                   Integer parentCategoryId, LocalDateTime createDate, LocalDateTime updateDate) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.parentCategoryId = parentCategoryId;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    // Minimal constructor with required fields
    public Category(String name) {
        this.name = name;
        this.status = "Active";
        this.createDate = LocalDateTime.now();
    }

    // Getters and Setters
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
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
        return name;
    }
    
}
