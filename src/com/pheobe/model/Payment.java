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

public class Payment {
    private int paymentID;
    private int orderID;
    private int customerID;
    private String name;
    private String paymentType;
    private BigDecimal shippingFee;
    private LocalDateTime paymentDate;
    private BigDecimal totalMoney;
    private int searchCount;
    private String description;
    private String status;

    // Default constructor
    public Payment() {
        this.shippingFee = BigDecimal.ZERO;
        this.paymentDate = LocalDateTime.now();
        this.searchCount = 0;
        this.status = "Pending";
    }

    // Parameterized constructor
    public Payment(int paymentID, int orderID, int customerID, String name, String paymentType, 
                  BigDecimal shippingFee, LocalDateTime paymentDate, BigDecimal totalMoney, 
                  int searchCount, String description, String status) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.customerID = customerID;
        this.name = name;
        this.paymentType = paymentType;
        this.shippingFee = shippingFee;
        this.paymentDate = paymentDate;
        this.totalMoney = totalMoney;
        this.searchCount = searchCount;
        this.description = description;
        this.status = status;
    }

    // Minimal constructor with required fields
    public Payment(int orderID, int customerID, String paymentType, BigDecimal totalMoney) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.paymentType = paymentType;
        this.totalMoney = totalMoney;
        this.shippingFee = BigDecimal.ZERO;
        this.paymentDate = LocalDateTime.now();
        this.searchCount = 0;
        this.status = "Pending";
    }

    // Getters and Setters
    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
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

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", orderID=" + orderID +
                ", customerID=" + customerID +
                ", name='" + name + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", shippingFee=" + shippingFee +
                ", paymentDate=" + paymentDate +
                ", totalMoney=" + totalMoney +
                ", searchCount=" + searchCount +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
