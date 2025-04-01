/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.service;
import com.pheobe.model.Category;
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
public class Category_DAO {
    private String queryToSelectAll = "SELECT * FROM Category";
    private String queryToSelectByID = "SELECT * FROM Category WHERE categoryId = ?";
    private String queryToInsert = "INSERT INTO Category (name, description, status, parentCategoryId, createDate, updateDate) VALUES (?, ?, ?, ?, ?, ?)";
    private String queryToInsertCategory = "INSERT INTO Category (name) VALUES (?)";
    private String queryToUpdate = "UPDATE Category SET Name = ? WHERE categoryId = ?";
    private String queryToDelete = "DELETE FROM Category WHERE categoryId = ?";
    
    private Connection con = DBcontext.getConnection();
    
    public ArrayList<Category> selectAll() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectAll);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setStatus(rs.getString("status"));
                category.setParentCategoryId(rs.getObject("parentCategoryId", Integer.class));
                category.setCreateDate(rs.getObject("createDate", LocalDateTime.class));
                category.setUpdateDate(rs.getObject("updateDate", LocalDateTime.class));
                categories.add(category);
            }
            rs.close();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    
    public Category selectById(int id) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectByID);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setStatus(rs.getString("status"));
                category.setParentCategoryId(rs.getObject("parentCategoryId", Integer.class));
                category.setCreateDate(rs.getObject("createDate", LocalDateTime.class));
                category.setUpdateDate(rs.getObject("updateDate", LocalDateTime.class));
                rs.close();
                pre.close();
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean insert(Category category) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToInsert);
            pre.setString(1, category.getName());
            pre.setString(2, category.getDescription());
            pre.setString(3, category.getStatus());
            pre.setObject(4, category.getParentCategoryId());
            pre.setObject(5, category.getCreateDate());
            pre.setObject(6, category.getUpdateDate());
            
            int row = pre.executeUpdate();
            pre.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean insertCategory(Category category) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToInsertCategory);
            pre.setString(1, category.getName());
            int row = pre.executeUpdate();
            pre.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(int CategoryID,Category category) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToUpdate);
            pre.setString(1, category.getName());
            pre.setInt(2, category.getCategoryId());
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
