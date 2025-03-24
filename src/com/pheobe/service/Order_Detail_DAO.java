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
    private static String getAllOrderDetails = "SELECT * FROM ORDER_DETAIL";
    private static String getOrderDetailById = "SELECT * FROM ORDER_DETAIL WHERE OrderDetailID = ?";
    private static String getOrderDetailsByOrderId = "SELECT * FROM ORDER_DETAIL WHERE OrderID = ?";
    private static String getOrderDetailsByProductId = "SELECT * FROM ORDER_DETAIL WHERE ProductID = ?";
    private static String insertOrderDetail = "INSERT INTO ORDER_DETAIL (OrderID, ProductID, price, quantity, status, ProductColorId) VALUES (?, ?, ?, ?, ?, ?)";
    private static String updateOrderDetail = "UPDATE ORDER_DETAIL SET OrderID = ?, ProductID = ?, price = ?, quantity = ?, status = ?, ProductColorId = ? WHERE OrderDetailID = ?";
    private static String deleteOrderDetail = "DELETE FROM ORDER_DETAIL WHERE OrderDetailID = ?";
    
    Connection con = DBcontext.getConnection();
    
    public List<Order_Detail> getAllOrderDetails() {
        List<Order_Detail> orderDetails = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getAllOrderDetails);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Order_Detail orderDetail = new Order_Detail();
                orderDetail.setOrderDetailID(rs.getInt("OrderDetailID"));
                orderDetail.setOrderID(rs.getInt("OrderID"));
                orderDetail.setProductID(rs.getInt("ProductID"));
                orderDetail.setPrice(rs.getBigDecimal("price"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setStatus(rs.getString("status"));
                orderDetail.setProductColorId(rs.getObject("ProductColorId", Integer.class));
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
                orderDetail.setOrderDetailID(rs.getInt("OrderDetailID"));
                orderDetail.setOrderID(rs.getInt("OrderID"));
                orderDetail.setProductID(rs.getInt("ProductID"));
                orderDetail.setPrice(rs.getBigDecimal("price"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setStatus(rs.getString("status"));
                orderDetail.setProductColorId(rs.getObject("ProductColorId", Integer.class));
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
                orderDetail.setOrderDetailID(rs.getInt("OrderDetailID"));
                orderDetail.setOrderID(rs.getInt("OrderID"));
                orderDetail.setProductID(rs.getInt("ProductID"));
                orderDetail.setPrice(rs.getBigDecimal("price"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setStatus(rs.getString("status"));
                orderDetail.setProductColorId(rs.getObject("ProductColorId", Integer.class));
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
                orderDetail.setOrderDetailID(rs.getInt("OrderDetailID"));
                orderDetail.setOrderID(rs.getInt("OrderID"));
                orderDetail.setProductID(rs.getInt("ProductID"));
                orderDetail.setPrice(rs.getBigDecimal("price"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setStatus(rs.getString("status"));
                orderDetail.setProductColorId(rs.getObject("ProductColorId", Integer.class));
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
