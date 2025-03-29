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

public class Brand {
    private int brandId;
    private String name;
    private int searchCount;
    private String description;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // Default constructor
    public Brand() {
        this.status = "Active";
        this.searchCount = 0;
        this.createDate = LocalDateTime.now();
    }

    // Parameterized constructor
    public Brand(int brandId, String name, int searchCount, String description, 
                String status, LocalDateTime createDate, LocalDateTime updateDate) {
        this.brandId = brandId;
        this.name = name;
        this.searchCount = searchCount;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    // Minimal constructor with required fields
    public Brand(String name) {
        this.name = name;
        this.status = "Active";
        this.searchCount = 0;
        this.createDate = LocalDateTime.now();
    }

    // Getters and Setters
    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
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
        return name;
    }
    
    
}