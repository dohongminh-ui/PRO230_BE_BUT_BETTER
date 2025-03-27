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

public class Customer {
    private int idCustomer;
    private String name;
    private String sex;
    private LocalDateTime createDate;
    private String email;
    private String phoneNumber;
    private String address;
    private String status;
    private String password;
    private String userName;
    private LocalDateTime logOut;
    private int accountFailCount;
    private boolean isAdmin;
    private String img;
    
    
    // Default constructor
    public Customer() {
        this.status = "Active";
        this.accountFailCount = 0;
        this.createDate = LocalDateTime.now();
        this.sex = "Unknown";
        this.phoneNumber = "";
        this.address = "";
    }

    public Customer(int idCustomer, String email, String password, String userName) {
        this.idCustomer = idCustomer;
        this.email = email;
        this.password = password;
        this.userName = userName;
    }
    
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    // Parameterized constructor
    public Customer(int idCustomer, String name, String sex, LocalDateTime createDate, String email, 
                   String phoneNumber, String address, String status, String password, 
                   String userName, LocalDateTime logOut, int accountFailCount) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.sex = sex;
        this.createDate = createDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
        this.password = password;
        this.userName = userName;
        this.logOut = logOut;
        this.accountFailCount = accountFailCount;
    }

    // Minimal constructor with required fields
    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = "Active";
        this.accountFailCount = 0;
        this.createDate = LocalDateTime.now();
    }

    // Getters and Setters
    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getLogOut() {
        return logOut;
    }

    public void setLogOut(LocalDateTime logOut) {
        this.logOut = logOut;
    }

    public int getAccountFailCount() {
        return accountFailCount;
    }

    public void setAccountFailCount(int accountFailCount) {
        this.accountFailCount = accountFailCount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return  address ;
    }
    
}
