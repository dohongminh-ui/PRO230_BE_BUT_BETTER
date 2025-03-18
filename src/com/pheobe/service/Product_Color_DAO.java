/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.service;
import com.pheobe.model.Product_Color;
import com.pheobe.connection.DBcontext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product_Color_DAO {
    private String queryString = "select * from product_color";
    private String insertStatement = "INSERT INTO Product_Color (ProductId, ColorId, ProductImage, Status) VALUES (?, ?, ?, ?)";
    private String updateStatement = "UPDATE Product_Color SET ProductId = ?, ColorId = ?, ProductImage = ?, Status = ? WHERE ProductColorId = ?";
    private String deleteStatement = "UPDATE Product_Color SET Status = ? WHERE ProductColorId = ?";
    
    Connection con = DBcontext.getConnection();
    
    public List<Product_Color> getAllProductColors() {
        List<Product_Color> productColors = new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement(queryString);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Product_Color productColor = new Product_Color();
                productColor.setProductColorId(rs.getInt("ProductColorId"));
                productColor.setProductId(rs.getInt("ProductId"));
                productColor.setColorId(rs.getInt("ColorId"));
                productColor.setProductImage(rs.getBytes("ProductImage"));
                productColor.setStatus(rs.getString("Status"));
                
                productColors.add(productColor);
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return productColors;
    }
    
    public boolean addProductColor(Product_Color productColor) {
        try {
            PreparedStatement stm = con.prepareStatement(insertStatement);
            stm.setInt(1, productColor.getProductId());
            stm.setInt(2, productColor.getColorId());
            stm.setBytes(3, productColor.getProductImage());
            stm.setString(4, productColor.getStatus());
            
            int row = stm.executeUpdate();
            stm.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean updateProductColor(int id, Product_Color productColor) {
        try {
            PreparedStatement stm = con.prepareStatement(updateStatement);
            stm.setInt(1, productColor.getProductId());
            stm.setInt(2, productColor.getColorId());
            stm.setBytes(3, productColor.getProductImage());
            stm.setString(4, productColor.getStatus());
            stm.setInt(5, id);
            
            int row = stm.executeUpdate();
            stm.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean deleteProductColor(int id) {
        try {
            PreparedStatement stm = con.prepareStatement(deleteStatement);
            stm.setString(1, "Inactive");
            stm.setInt(2, id);
            
            int row = stm.executeUpdate();
            stm.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public Product_Color getProductColorById(int id) {
        Product_Color productColor = null;
        try {
            String query = queryString + " WHERE ProductColorId = ?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                productColor = new Product_Color();
                productColor.setProductColorId(rs.getInt("ProductColorId"));
                productColor.setProductId(rs.getInt("ProductId"));
                productColor.setColorId(rs.getInt("ColorId"));
                productColor.setProductImage(rs.getBytes("ProductImage"));
                productColor.setStatus(rs.getString("Status"));
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return productColor;
    }
    
    // Additional methods based on business requirements
    
    public List<Product_Color> getProductColorsByProductId(int productId) {
        List<Product_Color> productColors = new ArrayList<>();
        try {
            String query = queryString + " WHERE ProductId = ? AND Status = 'Active'";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, productId);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Product_Color productColor = new Product_Color();
                productColor.setProductColorId(rs.getInt("ProductColorId"));
                productColor.setProductId(rs.getInt("ProductId"));
                productColor.setColorId(rs.getInt("ColorId"));
                productColor.setProductImage(rs.getBytes("ProductImage"));
                productColor.setStatus(rs.getString("Status"));
                
                productColors.add(productColor);
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return productColors;
    }
    
    public List<Product_Color> getProductColorsByColorId(int colorId) {
        List<Product_Color> productColors = new ArrayList<>();
        try {
            String query = queryString + " WHERE ColorId = ? AND Status = 'Active'";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, colorId);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Product_Color productColor = new Product_Color();
                productColor.setProductColorId(rs.getInt("ProductColorId"));
                productColor.setProductId(rs.getInt("ProductId"));
                productColor.setColorId(rs.getInt("ColorId"));
                productColor.setProductImage(rs.getBytes("ProductImage"));
                productColor.setStatus(rs.getString("Status"));
                
                productColors.add(productColor);
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return productColors;
    }
    
    public Product_Color getProductColorByProductAndColor(int productId, int colorId) {
        Product_Color productColor = null;
        try {
            String query = queryString + " WHERE ProductId = ? AND ColorId = ? AND Status = 'Active'";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, productId);
            stm.setInt(2, colorId);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                productColor = new Product_Color();
                productColor.setProductColorId(rs.getInt("ProductColorId"));
                productColor.setProductId(rs.getInt("ProductId"));
                productColor.setColorId(rs.getInt("ColorId"));
                productColor.setProductImage(rs.getBytes("ProductImage"));
                productColor.setStatus(rs.getString("Status"));
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return productColor;
    }
    
    public boolean updateProductImage(int id, byte[] image) {
        try {
            String updateImageQuery = "UPDATE Product_Color SET ProductImage = ? WHERE ProductColorId = ?";
            PreparedStatement stm = con.prepareStatement(updateImageQuery);
            stm.setBytes(1, image);
            stm.setInt(2, id);
            
            int row = stm.executeUpdate();
            stm.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
