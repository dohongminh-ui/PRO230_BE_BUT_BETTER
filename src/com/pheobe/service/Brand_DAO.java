/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.service;


import com.pheobe.connection.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.pheobe.model.Brand;
/**
 *
 * @author Admin
 */
public class Brand_DAO {
    private String queryToSelectAll = "SELECT * FROM Brand";
    private String queryToSelectByID = "SELECT * FROM Brand WHERE brandId = ?";
    private String queryToInsert = "INSERT INTO Brand (name) VALUES (?)";
    private String queryToUpdate = "UPDATE Brand SET Name = ? WHERE brandId = ?";
    private String queryToDelete = "DELETE FROM Brand WHERE brandId = ?";
    
    private Connection con = DBcontext.getConnection();
    
    public ArrayList<Brand> selectAll() {
        ArrayList<Brand> brands = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectAll);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setBrandId(rs.getInt("brandId"));
                brand.setName(rs.getString("name"));
                brand.setSearchCount(rs.getInt("searchCount"));
                brand.setDescription(rs.getString("description"));
                brand.setStatus(rs.getString("status"));
                brand.setCreateDate(rs.getObject("createDate", LocalDateTime.class));
                brand.setUpdateDate(rs.getObject("updateDate", LocalDateTime.class));
                brands.add(brand);
            }
            rs.close();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }
    
    public Brand selectById(int id) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectByID);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()) {
                Brand brand = new Brand();
                brand.setBrandId(rs.getInt("brandId"));
                brand.setName(rs.getString("name"));
                brand.setSearchCount(rs.getInt("searchCount"));
                brand.setDescription(rs.getString("description"));
                brand.setStatus(rs.getString("status"));
                brand.setCreateDate(rs.getObject("createDate", LocalDateTime.class));
                brand.setUpdateDate(rs.getObject("updateDate", LocalDateTime.class));
                rs.close();
                pre.close();
                return brand;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean insert(Brand brand) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToInsert);
            pre.setString(1, brand.getName());
            int row = pre.executeUpdate();
            pre.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(int brandId,Brand brand) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToUpdate);
            pre.setString(1, brand.getName());
            pre.setInt(2, brand.getBrandId());
            
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
