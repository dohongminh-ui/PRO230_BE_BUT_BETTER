/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.service;
import com.pheobe.model.Customer;
import com.pheobe.connection.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class Customer_DAO {
    private String queryToSelectAll = "SELECT * FROM Customer";
    private String queryToSelectByID = "SELECT * FROM Customer WHERE idCustomer = ?";
    private String queryToInsert = "INSERT INTO Customer (name, sex, createDate, email, phoneNumber, address, status, password, userName, logOut, accountFailCount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String queryToUpdate = "UPDATE Customer SET name = ?, sex = ?, email = ?, phoneNumber = ?, address = ?, status = ?, password = ?, userName = ?, logOut = ?, accountFailCount = ? WHERE idCustomer = ?";
    private String queryToDelete = "DELETE FROM Customer WHERE idCustomer = ?";
    
    private Connection con = DBcontext.getConnection();
    
    public ArrayList<Customer> selectAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectAll);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setIdCustomer(rs.getInt("idCustomer"));
                customer.setName(rs.getString("name"));
                customer.setSex(rs.getString("sex"));
                customer.setCreateDate(rs.getObject("createDate", LocalDateTime.class));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setAddress(rs.getString("address"));
                customer.setStatus(rs.getString("status"));
                customer.setPassword(rs.getString("password"));
                customer.setUserName(rs.getString("userName"));
                customer.setLogOut(rs.getObject("logOut", LocalDateTime.class));
                customer.setAccountFailCount(rs.getInt("accountFailCount"));
                customers.add(customer);
            }
            rs.close();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    
    public Customer selectById(int id) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectByID);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setIdCustomer(rs.getInt("idCustomer"));
                customer.setName(rs.getString("name"));
                customer.setSex(rs.getString("sex"));
                customer.setCreateDate(rs.getObject("createDate", LocalDateTime.class));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setAddress(rs.getString("address"));
                customer.setStatus(rs.getString("status"));
                customer.setPassword(rs.getString("password"));
                customer.setUserName(rs.getString("userName"));
                customer.setLogOut(rs.getObject("logOut", LocalDateTime.class));
                customer.setAccountFailCount(rs.getInt("accountFailCount"));
                rs.close();
                pre.close();
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean insert(Customer customer) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToInsert);
            pre.setString(1, customer.getName());
            pre.setString(2, customer.getSex());
            pre.setObject(3, customer.getCreateDate());
            pre.setString(4, customer.getEmail());
            pre.setString(5, customer.getPhoneNumber());
            pre.setString(6, customer.getAddress());
            pre.setString(7, customer.getStatus());
            pre.setString(8, customer.getPassword());
            pre.setString(9, customer.getUserName());
            pre.setObject(10, customer.getLogOut());
            pre.setInt(11, customer.getAccountFailCount());
            
            int row = pre.executeUpdate();
            pre.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(Customer customer) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToUpdate);
            pre.setString(1, customer.getName());
            pre.setString(2, customer.getSex());
            pre.setString(3, customer.getEmail());
            pre.setString(4, customer.getPhoneNumber());
            pre.setString(5, customer.getAddress());
            pre.setString(6, customer.getStatus());
            pre.setString(7, customer.getPassword());
            pre.setString(8, customer.getUserName());
            pre.setObject(9, customer.getLogOut());
            pre.setInt(10, customer.getAccountFailCount());
            pre.setInt(11, customer.getIdCustomer());
            
            int row = pre.executeUpdate();
            pre.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delete(int id) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToDelete);
            pre.setInt(1, id);
            
            int row = pre.executeUpdate();
            pre.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
