/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.service;

/**
 *
 * @author Admin
 */

import com.pheobe.connection.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.pheobe.model.Admin;

public class Admin_DAO {
    private String queryToSelectAll = "SELECT * FROM Admin";
    private String queryToSelectByID = "SELECT * FROM Admin WHERE ID = ?";
    private String queryToInsert = "INSERT INTO Admin (name, email, password, accountFailCount, createDate, lastLoginDate) VALUES (?, ?, ?, ?, ?, ?)";
    private String queryToUpdate = "UPDATE Admin SET name = ?, email = ?, password = ?, accountFailCount = ?, lastLoginDate = ? WHERE idAdmin = ?";
    private String queryToDelete = "DELETE FROM Admin WHERE idAdmin = ?";
    
    private Connection con = DBcontext.getConnection();
    
    public ArrayList<Admin> selectAll() {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectAll);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setIdAdmin(rs.getInt("idAdmin"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setAccountFailCount(rs.getInt("accountFailCount"));
                admin.setCreateDate(rs.getObject("createDate", LocalDateTime.class));
                admin.setLastLoginDate(rs.getObject("lastLoginDate", LocalDateTime.class));
                admins.add(admin);
            }
            rs.close();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }
    
    public Admin selectById(int id) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectByID);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setIdAdmin(rs.getInt("idAdmin"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
                admin.setAccountFailCount(rs.getInt("accountFailCount"));
                admin.setCreateDate(rs.getObject("createDate", LocalDateTime.class));
                admin.setLastLoginDate(rs.getObject("lastLoginDate", LocalDateTime.class));
                rs.close();
                pre.close();
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean insert(Admin admin) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToInsert);
            pre.setString(1, admin.getName());
            pre.setString(2, admin.getEmail());
            pre.setString(3, admin.getPassword());
            pre.setInt(4, admin.getAccountFailCount());
            pre.setObject(5, admin.getCreateDate());
            pre.setObject(6, admin.getLastLoginDate());
            
            int row = pre.executeUpdate();
            pre.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(Admin admin) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToUpdate);
            pre.setString(1, admin.getName());
            pre.setString(2, admin.getEmail());
            pre.setString(3, admin.getPassword());
            pre.setInt(4, admin.getAccountFailCount());
            pre.setObject(5, admin.getLastLoginDate());
            pre.setInt(6, admin.getIdAdmin());
            
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
