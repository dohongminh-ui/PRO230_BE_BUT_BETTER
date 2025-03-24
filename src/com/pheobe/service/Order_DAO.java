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
    private static String getAllOrders = "SELECT * FROM [ORDER]";
    private static String getOrderById = "SELECT * FROM [ORDER] WHERE OrderID = ?";
    private static String getOrdersByCustomerId = "SELECT * FROM [ORDER] WHERE CustomerID = ?";
    private static String getOrdersByStatus = "SELECT * FROM [ORDER] WHERE status = ?";
    private static String insertOrder = "INSERT INTO [ORDER] (CustomerID, ShippingAddress, ShippingPhoneNumber, CreateDate, Status) VALUES (?, ?, ?, ?, ?)";
    private static String updateOrder = "UPDATE ORDER SET CustomerID = ?, UpdateDate = ?, ShippingAddress = ?, ShippingPhoneNumber = ?, status = ?, Description = ?, ReasonForChange = ?, HistoryLogChange = ? WHERE OrderID = ?";
    private static String deleteOrder = "DELETE FROM [ORDER] WHERE OrderID = ?";
    
    Connection con = DBcontext.getConnection();
    
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getAllOrders);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getInt("OrderID"));
                order.setCustomerID(rs.getInt("CustomerID"));
                order.setCreateDate(rs.getObject("CreateDate", LocalDateTime.class));
                order.setUpdateDate(rs.getObject("UpdateDate", LocalDateTime.class));
                order.setShippingAddress(rs.getString("ShippingAddress"));
                order.setShippingPhoneNumber(rs.getString("ShippingPhoneNumber"));
                order.setStatus(rs.getString("Status"));
                order.setDescription(rs.getString("Description"));
                order.setReasonForChange(rs.getString("ReasonForChange"));
                order.setHistoryLogChange(rs.getString("HistoryLogChange"));
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
                order.setOrderID(rs.getInt("OrderID"));
                order.setCustomerID(rs.getInt("CustomerID"));
                order.setCreateDate(rs.getObject("CreateDate", LocalDateTime.class));
                order.setUpdateDate(rs.getObject("UpdateDate", LocalDateTime.class));
                order.setShippingAddress(rs.getString("ShippingAddress"));
                order.setShippingPhoneNumber(rs.getString("ShippingPhoneNumber"));
                order.setStatus(rs.getString("status"));
                order.setDescription(rs.getString("description"));
                order.setReasonForChange(rs.getString("ReasonForChange"));
                order.setHistoryLogChange(rs.getString("HistoryLogChange"));
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
                order.setOrderID(rs.getInt("OrderID"));
                order.setCustomerID(rs.getInt("CustomerID"));
                order.setCreateDate(rs.getObject("CreateDate", LocalDateTime.class));
                order.setUpdateDate(rs.getObject("UpdateDate", LocalDateTime.class));
                order.setShippingAddress(rs.getString("ShippingAddress"));
                order.setShippingPhoneNumber(rs.getString("ShippingPhoneNumber"));
                order.setStatus(rs.getString("Status"));
                order.setDescription(rs.getString("Description"));
                order.setReasonForChange(rs.getString("ReasonForChange"));
                order.setHistoryLogChange(rs.getString("HistoryLogChange"));
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
                order.setOrderID(rs.getInt("OrderID"));
                order.setCustomerID(rs.getInt("CustomerID"));
                order.setCreateDate(rs.getObject("CreateDate", LocalDateTime.class));
                order.setUpdateDate(rs.getObject("UpdateDate", LocalDateTime.class));
                order.setShippingAddress(rs.getString("ShippingAddress"));
                order.setShippingPhoneNumber(rs.getString("ShippingPhoneNumber"));
                order.setStatus(rs.getString("Status"));
                order.setDescription(rs.getString("Description"));
                order.setReasonForChange(rs.getString("ReasonForChange"));
                order.setHistoryLogChange(rs.getString("HistoryLogChange"));
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
            statement.setString(2, order.getShippingAddress());
            statement.setString(3, order.getShippingPhoneNumber());
            statement.setObject(4, order.getCreateDate());
            statement.setString(5, order.getStatus());
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