/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.model;

/**
 *
 * @author Admin
 */
public class Product_Color {
    private int productColorId;
    private int productId;
    private int colorId;
    private byte[] productImage;
    private String status;

    // Default constructor
    public Product_Color() {
        this.status = "Active";
    }

    // Parameterized constructor
    public Product_Color(int productColorId, int productId, int colorId, byte[] productImage, String status) {
        this.productColorId = productColorId;
        this.productId = productId;
        this.colorId = colorId;
        this.productImage = productImage;
        this.status = status;
    }

    // Minimal constructor with required fields
    public Product_Color(int productId, int colorId) {
        this.productId = productId;
        this.colorId = colorId;
        this.status = "Active";
    }

    // Getters and Setters
    public int getProductColorId() {
        return productColorId;
    }

    public void setProductColorId(int productColorId) {
        this.productColorId = productColorId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public void setProductImage(byte[] productImage) {
        this.productImage = productImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductColor{" +
                "productColorId=" + productColorId +
                ", productId=" + productId +
                ", colorId=" + colorId +
                ", status='" + status + '\'' +
                '}';
    }
}
