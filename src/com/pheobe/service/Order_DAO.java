/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.service;
import com.pheobe.model.Order;
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

public class Order_DAO {
    private static String getAllOrders = "SELECT * FROM orders";
    private static String getOrderById = "SELECT * FROM orders WHERE order_id = ?";
    private static String getOrdersByCustomerId = "SELECT * FROM orders WHERE customer_id = ?";
    private static String getOrdersByStatus = "SELECT * FROM orders WHERE status = ?";
    private static String insertOrder = "INSERT INTO orders (customer_id, create_date, shipping_address, shipping_phone_number, status, description) VALUES (?, ?, ?, ?, ?, ?)";
    private static String updateOrder = "UPDATE orders SET customer_id = ?, update_date = ?, shipping_address = ?, shipping_phone_number = ?, status = ?, description = ?, reason_for_change = ?, history_log_change = ? WHERE order_id = ?";
    private static String deleteOrder = "DELETE FROM orders WHERE order_id = ?";
    
    Connection con = DBcontext.getConnection();
    
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getAllOrders);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getInt("order_id"));
                order.setCustomerID(rs.getInt("customer_id"));
                order.setCreateDate(rs.getObject("create_date", LocalDateTime.class));
                order.setUpdateDate(rs.getObject("update_date", LocalDateTime.class));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setShippingPhoneNumber(rs.getString("shipping_phone_number"));
                order.setStatus(rs.getString("status"));
                order.setDescription(rs.getString("description"));
                order.setReasonForChange(rs.getString("reason_for_change"));
                order.setHistoryLogChange(rs.getString("history_log_change"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    public Order getOrderById(int id) {
        try {
            PreparedStatement statement = con.prepareStatement(getOrderById);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getInt("order_id"));
                order.setCustomerID(rs.getInt("customer_id"));
                order.setCreateDate(rs.getObject("create_date", LocalDateTime.class));
                order.setUpdateDate(rs.getObject("update_date", LocalDateTime.class));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setShippingPhoneNumber(rs.getString("shipping_phone_number"));
                order.setStatus(rs.getString("status"));
                order.setDescription(rs.getString("description"));
                order.setReasonForChange(rs.getString("reason_for_change"));
                order.setHistoryLogChange(rs.getString("history_log_change"));
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getOrdersByCustomerId);
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getInt("order_id"));
                order.setCustomerID(rs.getInt("customer_id"));
                order.setCreateDate(rs.getObject("create_date", LocalDateTime.class));
                order.setUpdateDate(rs.getObject("update_date", LocalDateTime.class));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setShippingPhoneNumber(rs.getString("shipping_phone_number"));
                order.setStatus(rs.getString("status"));
                order.setDescription(rs.getString("description"));
                order.setReasonForChange(rs.getString("reason_for_change"));
                order.setHistoryLogChange(rs.getString("history_log_change"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    public List<Order> getOrdersByStatus(String status) {
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getOrdersByStatus);
            statement.setString(1, status);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getInt("order_id"));
                order.setCustomerID(rs.getInt("customer_id"));
                order.setCreateDate(rs.getObject("create_date", LocalDateTime.class));
                order.setUpdateDate(rs.getObject("update_date", LocalDateTime.class));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setShippingPhoneNumber(rs.getString("shipping_phone_number"));
                order.setStatus(rs.getString("status"));
                order.setDescription(rs.getString("description"));
                order.setReasonForChange(rs.getString("reason_for_change"));
                order.setHistoryLogChange(rs.getString("history_log_change"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    public boolean insertOrder(Order order) {
        try {
            PreparedStatement statement = con.prepareStatement(insertOrder);
            statement.setInt(1, order.getCustomerID());
            statement.setObject(2, order.getCreateDate());
            statement.setString(3, order.getShippingAddress());
            statement.setString(4, order.getShippingPhoneNumber());
            statement.setString(5, order.getStatus());
            statement.setString(6, order.getDescription());
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateOrder(Order order) {
        try {
            PreparedStatement statement = con.prepareStatement(updateOrder);
            statement.setInt(1, order.getCustomerID());
            statement.setObject(2, LocalDateTime.now());
            statement.setString(3, order.getShippingAddress());
            statement.setString(4, order.getShippingPhoneNumber());
            statement.setString(5, order.getStatus());
            statement.setString(6, order.getDescription());
            statement.setString(7, order.getReasonForChange());
            statement.setString(8, order.getHistoryLogChange());
            statement.setInt(9, order.getOrderID());
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteOrder(int id) {
        try {
            PreparedStatement statement = con.prepareStatement(deleteOrder);
            statement.setInt(1, id);
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}