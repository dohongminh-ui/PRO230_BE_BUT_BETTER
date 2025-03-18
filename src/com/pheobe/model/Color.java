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

public class Color {
    private int colorId;
    private String name;
    private String hexCode;
    private byte[] image;
    private LocalDateTime createOrUpdateDate;

    // Default constructor
    public Color() {
        this.createOrUpdateDate = LocalDateTime.now();
    }

    // Parameterized constructor
    public Color(int colorId, String name, String hexCode, byte[] image, LocalDateTime createOrUpdateDate) {
        this.colorId = colorId;
        this.name = name;
        this.hexCode = hexCode;
        this.image = image;
        this.createOrUpdateDate = createOrUpdateDate;
    }

    // Minimal constructor with required fields
    public Color(String name, String hexCode) {
        this.name = name;
        this.hexCode = hexCode;
        this.createOrUpdateDate = LocalDateTime.now();
    }

    // Getters and Setters
    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public LocalDateTime getCreateOrUpdateDate() {
        return createOrUpdateDate;
    }

    public void setCreateOrUpdateDate(LocalDateTime createOrUpdateDate) {
        this.createOrUpdateDate = createOrUpdateDate;
    }

}
