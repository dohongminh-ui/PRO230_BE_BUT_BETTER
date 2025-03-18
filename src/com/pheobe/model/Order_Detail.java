/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.model;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */

public class Order_Detail {
    private int orderDetailID;
    private int orderID;
    private int productID;
    private BigDecimal price;
    private int quantity;
    private String status;
    private Integer productColorId;

    // Default constructor
    public Order_Detail() {
        this.quantity = 1;
        this.status = "Active";
    }

    // Parameterized constructor
    public Order_Detail(int orderDetailID, int orderID, int productID, BigDecimal price, 
                      int quantity, String status, Integer productColorId) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.productColorId = productColorId;
    }

    // Minimal constructor with required fields
    public Order_Detail(int orderID, int productID, BigDecimal price) {
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.quantity = 1;
        this.status = "Active";
    }

    // Getters and Setters
    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalMoney() {
        if (price == null) {
            return BigDecimal.ZERO;
        }
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProductColorId() {
        return productColorId;
    }

    public void setProductColorId(Integer productColorId) {
        this.productColorId = productColorId;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailID=" + orderDetailID +
                ", orderID=" + orderID +
                ", productID=" + productID +
                ", price=" + price +
                ", quantity=" + quantity +
                ", totalMoney=" + getTotalMoney() +
                ", status='" + status + '\'' +
                ", productColorId=" + productColorId +
                '}';
    }
}
