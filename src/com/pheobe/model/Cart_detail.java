/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.model;import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 *
 * @author Admin
 */
public class Cart_detail {


    private int cartDetailID;
    private int cartID;
    private int productId;
    private BigDecimal price;
    private int quantity;
    private String status;
    private LocalDateTime createDate;
    private Integer productColorId;

    public Cart_detail() {
    }

    public Cart_detail(int cartDetailID, int cartID, int productId, BigDecimal price, int quantity, String status, LocalDateTime createDate, Integer productColorId) {
        this.cartDetailID = cartDetailID;
        this.cartID = cartID;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.createDate = createDate;
        this.productColorId = productColorId;
    }

    public int getCartDetailID() {
        return cartDetailID;
    }

    public void setCartDetailID(int cartDetailID) {
        this.cartDetailID = cartDetailID;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public Integer getProductColorId() {
        return productColorId;
    }

    public void setProductColorId(Integer productColorId) {
        this.productColorId = productColorId;
    }
    
    
}

    
    
    


