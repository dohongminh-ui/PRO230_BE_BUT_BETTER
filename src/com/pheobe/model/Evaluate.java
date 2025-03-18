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

public class Evaluate {
    private int id;
    private int productId;
    private int customerId;
    private String comment;
    private Integer rating;
    private LocalDateTime date;
    private LocalDateTime updateDate;

    // Default constructor
    public Evaluate() {
        this.date = LocalDateTime.now();
    }

    // Parameterized constructor
    public Evaluate(int id, int productId, int customerId, String comment, Integer rating, 
                   LocalDateTime date, LocalDateTime updateDate) {
        this.id = id;
        this.productId = productId;
        this.customerId = customerId;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
        this.updateDate = updateDate;
    }

    // Minimal constructor with required fields
    public Evaluate(int productId, int customerId) {
        this.productId = productId;
        this.customerId = customerId;
        this.date = LocalDateTime.now();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = rating;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }


}
