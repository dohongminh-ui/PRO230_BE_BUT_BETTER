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

public class Order {
    private int orderID;
    private int customerID;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String shippingAddress;
    private String shippingPhoneNumber;
    private String status;
    private String description;
    private String reasonForChange;
    private String historyLogChange;

    // Default constructor
    public Order() {
        this.status = "Pending";
        this.createDate = LocalDateTime.now();
    }

    // Parameterized constructor
    public Order(int orderID, int customerID, LocalDateTime createDate, LocalDateTime updateDate, 
                String shippingAddress, String shippingPhoneNumber, String status, 
                String description, String reasonForChange, String historyLogChange) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.shippingAddress = shippingAddress;
        this.shippingPhoneNumber = shippingPhoneNumber;
        this.status = status;
        this.description = description;
        this.reasonForChange = reasonForChange;
        this.historyLogChange = historyLogChange;
    }

    // Minimal constructor with required fields
    public Order(int customerID, String shippingAddress, String shippingPhoneNumber) {
        this.customerID = customerID;
        this.shippingAddress = shippingAddress;
        this.shippingPhoneNumber = shippingPhoneNumber;
        this.status = "Pending";
        this.createDate = LocalDateTime.now();
    }

    // Getters and Setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingPhoneNumber() {
        return shippingPhoneNumber;
    }

    public void setShippingPhoneNumber(String shippingPhoneNumber) {
        this.shippingPhoneNumber = shippingPhoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReasonForChange() {
        return reasonForChange;
    }

    public void setReasonForChange(String reasonForChange) {
        this.reasonForChange = reasonForChange;
    }

    public String getHistoryLogChange() {
        return historyLogChange;
    }

    public void setHistoryLogChange(String historyLogChange) {
        this.historyLogChange = historyLogChange;
    }


}