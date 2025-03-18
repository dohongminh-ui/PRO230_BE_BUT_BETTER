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

public class Cart {
    private int id;
    private int customerId;
    private String cartID;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // Default constructor
    public Cart() {
        this.status = "Active";
        this.createDate = LocalDateTime.now();
    }

    // Parameterized constructor
    public Cart(int id, int customerId, String cartID, String status, 
               LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.customerId = customerId;
        this.cartID = cartID;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    // Minimal constructor with required fields
    public Cart(int customerId, String cartID) {
        this.customerId = customerId;
        this.cartID = cartID;
        this.status = "Active";
        this.createDate = LocalDateTime.now();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
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

}