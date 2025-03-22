/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.service;
import com.pheobe.model.Order_Detail;
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
public class Order_Detail_DAO {
    private static String getAllOrderDetails = "SELECT * FROM order_detail";
    private static String getOrderDetailById = "SELECT * FROM order_detail WHERE order_detail_id = ?";
    private static String getOrderDetailsByOrderId = "SELECT * FROM order_detail WHERE order_id = ?";
    private static String getOrderDetailsByProductId = "SELECT * FROM order_detail WHERE product_id = ?";
    private static String insertOrderDetail = "INSERT INTO order_detail (order_id, product_id, price, quantity, status, product_color_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static String updateOrderDetail = "UPDATE order_detail SET order_id = ?, product_id = ?, price = ?, quantity = ?, status = ?, product_color_id = ? WHERE order_detail_id = ?";
    private static String deleteOrderDetail = "DELETE FROM order_detail WHERE order_detail_id = ?";
    
    Connection con = DBcontext.getConnection();
    
    public List<Order_Detail> getAllOrderDetails() {
        List<Order_Detail> orderDetails = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getAllOrderDetails);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Order_Detail orderDetail = new Order_Detail();
                orderDetail.setOrderDetailID(rs.getInt("order_detail_id"));
                orderDetail.setOrderID(rs.getInt("order_id"));
                orderDetail.setProductID(rs.getInt("product_id"));
                orderDetail.setPrice(rs.getBigDecimal("price"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setStatus(rs.getString("status"));
                orderDetail.setProductColorId(rs.getObject("product_color_id", Integer.class));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }
    
    public Order_Detail getOrderDetailById(int id) {
        try {
            PreparedStatement statement = con.prepareStatement(getOrderDetailById);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                Order_Detail orderDetail = new Order_Detail();
                orderDetail.setOrderDetailID(rs.getInt("order_detail_id"));
                orderDetail.setOrderID(rs.getInt("order_id"));
                orderDetail.setProductID(rs.getInt("product_id"));
                orderDetail.setPrice(rs.getBigDecimal("price"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setStatus(rs.getString("status"));
                orderDetail.setProductColorId(rs.getObject("product_color_id", Integer.class));
                return orderDetail;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Order_Detail> getOrderDetailsByOrderId(int orderId) {
        List<Order_Detail> orderDetails = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getOrderDetailsByOrderId);
            statement.setInt(1, orderId);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Order_Detail orderDetail = new Order_Detail();
                orderDetail.setOrderDetailID(rs.getInt("order_detail_id"));
                orderDetail.setOrderID(rs.getInt("order_id"));
                orderDetail.setProductID(rs.getInt("product_id"));
                orderDetail.setPrice(rs.getBigDecimal("price"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setStatus(rs.getString("status"));
                orderDetail.setProductColorId(rs.getObject("product_color_id", Integer.class));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }
    
    public List<Order_Detail> getOrderDetailsByProductId(int productId) {
        List<Order_Detail> orderDetails = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getOrderDetailsByProductId);
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Order_Detail orderDetail = new Order_Detail();
                orderDetail.setOrderDetailID(rs.getInt("order_detail_id"));
                orderDetail.setOrderID(rs.getInt("order_id"));
                orderDetail.setProductID(rs.getInt("product_id"));
                orderDetail.setPrice(rs.getBigDecimal("price"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setStatus(rs.getString("status"));
                orderDetail.setProductColorId(rs.getObject("product_color_id", Integer.class));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }
    
    public boolean insertOrderDetail(Order_Detail orderDetail) {
        try {
            PreparedStatement statement = con.prepareStatement(insertOrderDetail);
            statement.setInt(1, orderDetail.getOrderID());
            statement.setInt(2, orderDetail.getProductID());
            statement.setBigDecimal(3, orderDetail.getPrice());
            statement.setInt(4, orderDetail.getQuantity());
            statement.setString(5, orderDetail.getStatus());
            statement.setObject(6, orderDetail.getProductColorId());
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateOrderDetail(Order_Detail orderDetail) {
        try {
            PreparedStatement statement = con.prepareStatement(updateOrderDetail);
            statement.setInt(1, orderDetail.getOrderID());
            statement.setInt(2, orderDetail.getProductID());
            statement.setBigDecimal(3, orderDetail.getPrice());
            statement.setInt(4, orderDetail.getQuantity());
            statement.setString(5, orderDetail.getStatus());
            statement.setObject(6, orderDetail.getProductColorId());
            statement.setInt(7, orderDetail.getOrderDetailID());
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean OrderDetailGetProductname(Order_Detail orderDetail) {
        try {
            PreparedStatement statement = con.prepareStatement(updateOrderDetail);
            statement.setInt(1, orderDetail.getOrderID());
            statement.setInt(2, orderDetail.getProductID());
            statement.setBigDecimal(3, orderDetail.getPrice());
            statement.setInt(4, orderDetail.getQuantity());
            statement.setString(5, orderDetail.getStatus());
            statement.setObject(6, orderDetail.getProductColorId());
            statement.setInt(7, orderDetail.getOrderDetailID());
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    public boolean deleteOrderDetail(int id) {
        try {
            PreparedStatement statement = con.prepareStatement(deleteOrderDetail);
            statement.setInt(1, id);
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
