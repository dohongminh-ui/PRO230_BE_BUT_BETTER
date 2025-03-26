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
import com.pheobe.model.Cart;
/**
 *
 * @author Admin
 */
public class Cart_DAO {
    private String queryToSelectAll = "SELECT * FROM Cart";
    private String queryToSelectByID = "SELECT * FROM Cart WHERE id = ?";
    private String queryToSelectByIDCustomer = "SELECT * FROM Cart WHERE CustomerID = ?";
    private String queryToInsert = "INSERT INTO Cart (customerId, cartID, status, createDate, updateDate) VALUES (?, ?, ?, ?, ?)";
    private String queryToUpdate = "UPDATE Cart SET customerId = ?, cartID = ?, status = ?, updateDate = ? WHERE id = ?";
    private String queryToDelete = "DELETE FROM Cart WHERE id = ?";
    
    private Connection con = DBcontext.getConnection();
    
    public ArrayList<Cart> selectAll() {
        ArrayList<Cart> carts = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectAll);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setCustomerId(rs.getInt("customerId"));
                cart.setCartID(rs.getString("cartID"));
                cart.setStatus(rs.getString("status"));
                cart.setCreateDate(rs.getObject("createDate", LocalDateTime.class));
                cart.setUpdateDate(rs.getObject("updateDate", LocalDateTime.class));
                carts.add(cart);
            }
            rs.close();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carts;
    }
    
    public Cart selectByIdCusomer(int id) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectByIDCustomer);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setCustomerId(rs.getInt("customerId"));
                cart.setCartID(rs.getString("cartID"));
                cart.setStatus(rs.getString("status"));
                cart.setCreateDate(rs.getObject("createDate", LocalDateTime.class));
                cart.setUpdateDate(rs.getObject("updateDate", LocalDateTime.class));
                rs.close();
                pre.close();
                return cart;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Cart selectById(int id) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectByID);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setCustomerId(rs.getInt("customerId"));
                cart.setCartID(rs.getString("cartID"));
                cart.setStatus(rs.getString("status"));
                cart.setCreateDate(rs.getObject("createDate", LocalDateTime.class));
                cart.setUpdateDate(rs.getObject("updateDate", LocalDateTime.class));
                rs.close();
                pre.close();
                return cart;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean insert(Cart cart) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToInsert);
            pre.setInt(1, cart.getCustomerId());
            pre.setString(2, cart.getCartID());
            pre.setString(3, cart.getStatus());
            pre.setObject(4, cart.getCreateDate());
            pre.setObject(5, cart.getUpdateDate());
            
            int row = pre.executeUpdate();
            pre.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(Cart cart) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToUpdate);
            pre.setInt(1, cart.getCustomerId());
            pre.setString(2, cart.getCartID());
            pre.setString(3, cart.getStatus());
            pre.setObject(4, cart.getUpdateDate());
            pre.setInt(5, cart.getId());
            
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