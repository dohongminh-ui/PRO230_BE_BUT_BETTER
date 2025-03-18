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

public class Delivery_Address {
    private int id;
    private int customerId;
    private String name;
    private String address;
    private String phoneNumber;
    private boolean isDefault;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // Default constructor
    public Delivery_Address() {
        this.isDefault = false;
        this.createDate = LocalDateTime.now();
    }

    // Parameterized constructor
    public Delivery_Address(int id, int customerId, String name, String address, String phoneNumber, 
                          boolean isDefault, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isDefault = isDefault;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    // Minimal constructor with required fields
    public Delivery_Address(int customerId, String name, String address, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isDefault = false;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
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
