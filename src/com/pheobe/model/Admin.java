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

public class Admin {
    private int idAdmin;
    private String name;
    private String email;
    private String password;
    private int accountFailCount;
    private LocalDateTime createDate;
    private LocalDateTime lastLoginDate;

    // Default constructor
    public Admin() {
        this.accountFailCount = 0;
        this.createDate = LocalDateTime.now();
    }

    // Parameterized constructor
    public Admin(int idAdmin, String name, String email, String password, int accountFailCount, 
                LocalDateTime createDate, LocalDateTime lastLoginDate) {
        this.idAdmin = idAdmin;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountFailCount = accountFailCount;
        this.createDate = createDate;
        this.lastLoginDate = lastLoginDate;
    }

    
    // Minimal constructor with required fields
    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountFailCount = 0;
        this.createDate = LocalDateTime.now();
    }

    // Getters and Setters
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountFailCount() {
        return accountFailCount;
    }

    public void setAccountFailCount(int accountFailCount) {
        this.accountFailCount = accountFailCount;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
