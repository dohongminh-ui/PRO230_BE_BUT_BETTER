/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.service;
import com.pheobe.connection.DBcontext;
import com.pheobe.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Product_DAO {
    private String queryString = "select * from product";
    private String insertStatement = "INSERT INTO Product (Name, CategoryId, BrandId, Price, Stock, Description, Status, CreateDate, UpdateDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String InsertProduct = "INSERT INTO Product (Name, CategoryId, BrandId, Price, Stock, Description, Image) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private String updateStatement = "UPDATE Product SET Name = ?, CategoryId = ?, BrandId = ?, Price = ?, Stock = ?, Description = ?,  Image = ? WHERE IdProduct = ?";
    private String deleteStatement = "DELETE Product  WHERE IdProduct = ?";
    
    Connection cn = DBcontext.getConnection();
    
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement stm = cn.prepareStatement(queryString);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
                product.setIdProduct(rs.getInt("IdProduct"));
                product.setName(rs.getString("Name"));
                product.setCategoryId(rs.getInt("CategoryId"));
                product.setBrandId(rs.getInt("BrandId"));
                product.setPrice(rs.getBigDecimal("Price"));
                product.setStock(rs.getInt("Stock"));
                product.setDescription(rs.getString("Description"));
                product.setStatus(rs.getString("Status"));
                product.setImg(rs.getString("Image"));
                // Convert SQL Timestamp to LocalDateTime
                Timestamp createTimestamp = rs.getTimestamp("CreateDate");
                product.setCreateDate(createTimestamp != null ? createTimestamp.toLocalDateTime() : null);
                
                Timestamp updateTimestamp = rs.getTimestamp("UpdateDate");
                product.setUpdateDate(updateTimestamp != null ? updateTimestamp.toLocalDateTime() : null);
                
                products.add(product);
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    public boolean addProduct(Product product) {
        try {
            PreparedStatement stm = cn.prepareStatement(insertStatement);
            stm.setString(1, product.getName());
            stm.setInt(2, product.getCategoryId());
            stm.setInt(3, product.getBrandId());
            stm.setBigDecimal(4, product.getPrice());
            stm.setInt(5, product.getStock());
            stm.setString(6, product.getDescription());
            stm.setString(7, product.getStatus());
            stm.setTimestamp(8, product.getCreateDate() != null ? Timestamp.valueOf(product.getCreateDate()) : Timestamp.valueOf(LocalDateTime.now()));
            stm.setTimestamp(9, product.getUpdateDate() != null ? Timestamp.valueOf(product.getUpdateDate()) : null);
            
            int row = stm.executeUpdate();
            stm.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean addProductBB(Product product) {
        try {
            PreparedStatement stm = cn.prepareStatement(InsertProduct);
            stm.setString(1, product.getName());
            stm.setInt(2, product.getCategoryId());
            stm.setInt(3, product.getBrandId());
            stm.setBigDecimal(4, product.getPrice());
            stm.setInt(5, product.getStock());
            stm.setString(6, product.getDescription());
            stm.setString(7, product.getImg());

            
            int row = stm.executeUpdate();
            stm.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean updateProduct(int id, Product product) {
        try {
            PreparedStatement stm = cn.prepareStatement(updateStatement);
            stm.setString(1, product.getName());
            stm.setInt(2, product.getCategoryId());
            stm.setInt(3, product.getBrandId());
            stm.setBigDecimal(4, product.getPrice());
            stm.setInt(5, product.getStock());
            stm.setString(6, product.getDescription());
            stm.setString(7, product.getImg());
            stm.setInt(8, id);
            
            int row = stm.executeUpdate();
            stm.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean deleteProduct(int id) {
        try {
            PreparedStatement stm = cn.prepareStatement(deleteStatement);
            stm.setInt(1, id);
            
            int row = stm.executeUpdate();
            stm.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public Product getProductById(int id) {
        Product product = null;
        try {
            String query = queryString + " WHERE IdProduct = ?";
            PreparedStatement stm = cn.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                product = new Product();
                product.setIdProduct(rs.getInt("IdProduct"));
                product.setName(rs.getString("Name"));
                product.setCategoryId(rs.getInt("CategoryId"));
                product.setBrandId(rs.getInt("BrandId"));
                product.setPrice(rs.getBigDecimal("Price"));
                product.setStock(rs.getInt("Stock"));
                product.setDescription(rs.getString("Description"));
                product.setStatus(rs.getString("Status"));
                
                // Convert SQL Timestamp to LocalDateTime
                Timestamp createTimestamp = rs.getTimestamp("CreateDate");
                product.setCreateDate(createTimestamp != null ? createTimestamp.toLocalDateTime() : null);
                
                Timestamp updateTimestamp = rs.getTimestamp("UpdateDate");
                product.setUpdateDate(updateTimestamp != null ? updateTimestamp.toLocalDateTime() : null);
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return product;
    }
    
    // Additional methods based on business requirements
    
    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> products = new ArrayList<>();
        try {
            String query = queryString + " WHERE CategoryId = ? AND Status = 'Active'";
            PreparedStatement stm = cn.prepareStatement(query);
            stm.setInt(1, categoryId);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
                product.setIdProduct(rs.getInt("IdProduct"));
                product.setName(rs.getString("Name"));
                product.setCategoryId(rs.getInt("CategoryId"));
                product.setBrandId(rs.getInt("BrandId"));
                product.setPrice(rs.getBigDecimal("Price"));
                product.setStock(rs.getInt("Stock"));
                product.setDescription(rs.getString("Description"));
                product.setStatus(rs.getString("Status"));
                product.setImg(rs.getString("Image"));
                
                // Convert SQL Timestamp to LocalDateTime
                Timestamp createTimestamp = rs.getTimestamp("CreateDate");
                product.setCreateDate(createTimestamp != null ? createTimestamp.toLocalDateTime() : null);
                
                Timestamp updateTimestamp = rs.getTimestamp("UpdateDate");
                product.setUpdateDate(updateTimestamp != null ? updateTimestamp.toLocalDateTime() : null);
                
                products.add(product);
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    public List<Product> getProductsByBrandId(int brandId) {
        List<Product> products = new ArrayList<>();
        try {
            String query = queryString + " WHERE BrandId = ? AND Status = 'Active'";
            PreparedStatement stm = cn.prepareStatement(query);
            stm.setInt(1, brandId);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
                product.setIdProduct(rs.getInt("IdProduct"));
                product.setName(rs.getString("Name"));
                product.setCategoryId(rs.getInt("CategoryId"));
                product.setBrandId(rs.getInt("BrandId"));
                product.setPrice(rs.getBigDecimal("Price"));
                product.setStock(rs.getInt("Stock"));
                product.setDescription(rs.getString("Description"));
                product.setStatus(rs.getString("Status"));
                product.setImg(rs.getString("Image"));
                
                // Convert SQL Timestamp to LocalDateTime
                Timestamp createTimestamp = rs.getTimestamp("CreateDate");
                product.setCreateDate(createTimestamp != null ? createTimestamp.toLocalDateTime() : null);
                
                Timestamp updateTimestamp = rs.getTimestamp("UpdateDate");
                product.setUpdateDate(updateTimestamp != null ? updateTimestamp.toLocalDateTime() : null);
                
                products.add(product);
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    public List<Product> searchProductsByName(String keyword) {
        List<Product> products = new ArrayList<>();
        try {
            String query = queryString + " WHERE Name LIKE ? AND Status = 'Active'";
            PreparedStatement stm = cn.prepareStatement(query);
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
                product.setIdProduct(rs.getInt("IdProduct"));
                product.setName(rs.getString("Name"));
                product.setCategoryId(rs.getInt("CategoryId"));
                product.setBrandId(rs.getInt("BrandId"));
                product.setPrice(rs.getBigDecimal("Price"));
                product.setStock(rs.getInt("Stock"));
                product.setDescription(rs.getString("Description"));
                product.setStatus(rs.getString("Status"));
                
                // Convert SQL Timestamp to LocalDateTime
                Timestamp createTimestamp = rs.getTimestamp("CreateDate");
                product.setCreateDate(createTimestamp != null ? createTimestamp.toLocalDateTime() : null);
                
                Timestamp updateTimestamp = rs.getTimestamp("UpdateDate");
                product.setUpdateDate(updateTimestamp != null ? updateTimestamp.toLocalDateTime() : null);
                
                products.add(product);
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    public boolean updateProductStock(int id, int stockChange) {
        try {
            Product product = getProductById(id);
            if (product == null) return false;
            
            int newStock = product.getStock() + stockChange;
            if (newStock < 0) newStock = 0; // Ensure stock doesn't go negative
            
            String updateStockQuery = "UPDATE Product SET Stock = ?, UpdateDate = ? WHERE IdProduct = ?";
            PreparedStatement stm = cn.prepareStatement(updateStockQuery);
            stm.setInt(1, newStock);
            stm.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stm.setInt(3, id);
            
            int row = stm.executeUpdate();
            stm.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
}
