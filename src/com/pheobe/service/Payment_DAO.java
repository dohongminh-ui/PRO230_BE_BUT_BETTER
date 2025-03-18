/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.service;
import com.pheobe.model.Payment;
import com.pheobe.connection.DBcontext;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class Payment_DAO {
    private String queryString = "select * from payment";
    private String insertStatement = "INSERT INTO Payment (OrderID, CustomerID, Name, PaymentType, ShippingFee, PaymentDate, TotalMoney, SearchCount, Description, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String updateStatement = "UPDATE Payment SET OrderID = ?, CustomerID = ?, Name = ?, PaymentType = ?, ShippingFee = ?, PaymentDate = ?, TotalMoney = ?, SearchCount = ?, Description = ?, Status = ? WHERE ID = ?";
    private String deleteStatement = "UPDATE Payment SET Status = ? WHERE ID = ?";
    
    Connection con = DBcontext.getConnection();
    
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement(queryString);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentID(rs.getInt("PaymentID"));
                payment.setOrderID(rs.getInt("OrderID"));
                payment.setCustomerID(rs.getInt("CustomerID"));
                payment.setName(rs.getString("Name"));
                payment.setPaymentType(rs.getString("PaymentType"));
                payment.setShippingFee(rs.getBigDecimal("ShippingFee"));
                
                // Convert SQL Timestamp to LocalDateTime
                Timestamp timestamp = rs.getTimestamp("PaymentDate");
                payment.setPaymentDate(timestamp != null ? timestamp.toLocalDateTime() : null);
                
                payment.setTotalMoney(rs.getBigDecimal("TotalMoney"));
                payment.setSearchCount(rs.getInt("SearchCount"));
                payment.setDescription(rs.getString("Description"));
                payment.setStatus(rs.getString("Status"));
                
                payments.add(payment);
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return payments;
    }
    
    public boolean addPayment(Payment payment) {
        try {
            PreparedStatement stm = con.prepareStatement(insertStatement);
            stm.setInt(1, payment.getOrderID());
            stm.setInt(2, payment.getCustomerID());
            stm.setString(3, payment.getName());
            stm.setString(4, payment.getPaymentType());
            stm.setBigDecimal(5, payment.getShippingFee());
            stm.setTimestamp(6, payment.getPaymentDate() != null ? Timestamp.valueOf(payment.getPaymentDate()) : null);
            stm.setBigDecimal(7, payment.getTotalMoney());
            stm.setInt(8, payment.getSearchCount());
            stm.setString(9, payment.getDescription());
            stm.setString(10, payment.getStatus());
            
            int row = stm.executeUpdate();
            stm.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean updatePayment(int id, Payment payment) {
        try {
            PreparedStatement stm = con.prepareStatement(updateStatement);
            stm.setInt(1, payment.getOrderID());
            stm.setInt(2, payment.getCustomerID());
            stm.setString(3, payment.getName());
            stm.setString(4, payment.getPaymentType());
            stm.setBigDecimal(5, payment.getShippingFee());
            stm.setTimestamp(6, payment.getPaymentDate() != null ? Timestamp.valueOf(payment.getPaymentDate()) : null);
            stm.setBigDecimal(7, payment.getTotalMoney());
            stm.setInt(8, payment.getSearchCount());
            stm.setString(9, payment.getDescription());
            stm.setString(10, payment.getStatus());
            stm.setInt(11, id);
            
            int row = stm.executeUpdate();
            stm.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean deletePayment(int id) {
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
    
    public Payment getPaymentById(int id) {
        Payment payment = null;
        try {
            String query = queryString + " WHERE PaymentID = ?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                payment = new Payment();
                payment.setPaymentID(rs.getInt("PaymentID"));
                payment.setOrderID(rs.getInt("OrderID"));
                payment.setCustomerID(rs.getInt("CustomerID"));
                payment.setName(rs.getString("Name"));
                payment.setPaymentType(rs.getString("PaymentType"));
                payment.setShippingFee(rs.getBigDecimal("ShippingFee"));
                
                // Convert SQL Timestamp to LocalDateTime
                Timestamp timestamp = rs.getTimestamp("PaymentDate");
                payment.setPaymentDate(timestamp != null ? timestamp.toLocalDateTime() : null);
                
                payment.setTotalMoney(rs.getBigDecimal("TotalMoney"));
                payment.setSearchCount(rs.getInt("SearchCount"));
                payment.setDescription(rs.getString("Description"));
                payment.setStatus(rs.getString("Status"));
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return payment;
    }
    
    // Additional methods based on your requirements
    
    public List<Payment> getPaymentsByCustomerId(int customerId) {
        List<Payment> payments = new ArrayList<>();
        try {
            String query = queryString + " WHERE CustomerID = ?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, customerId);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentID(rs.getInt("PaymentID"));
                payment.setOrderID(rs.getInt("OrderID"));
                payment.setCustomerID(rs.getInt("CustomerID"));
                payment.setName(rs.getString("Name"));
                payment.setPaymentType(rs.getString("PaymentType"));
                payment.setShippingFee(rs.getBigDecimal("ShippingFee"));
                
                // Convert SQL Timestamp to LocalDateTime
                Timestamp timestamp = rs.getTimestamp("PaymentDate");
                payment.setPaymentDate(timestamp != null ? timestamp.toLocalDateTime() : null);
                
                payment.setTotalMoney(rs.getBigDecimal("TotalMoney"));
                payment.setSearchCount(rs.getInt("SearchCount"));
                payment.setDescription(rs.getString("Description"));
                payment.setStatus(rs.getString("Status"));
                
                payments.add(payment);
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return payments;
    }
    
    public List<Payment> getPaymentsByOrderId(int orderId) {
        List<Payment> payments = new ArrayList<>();
        try {
            String query = queryString + " WHERE OrderID = ?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, orderId);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentID(rs.getInt("PaymentID"));
                payment.setOrderID(rs.getInt("OrderID"));
                payment.setCustomerID(rs.getInt("CustomerID"));
                payment.setName(rs.getString("Name"));
                payment.setPaymentType(rs.getString("PaymentType"));
                payment.setShippingFee(rs.getBigDecimal("ShippingFee"));
                
                // Convert SQL Timestamp to LocalDateTime
                Timestamp timestamp = rs.getTimestamp("PaymentDate");
                payment.setPaymentDate(timestamp != null ? timestamp.toLocalDateTime() : null);
                
                payment.setTotalMoney(rs.getBigDecimal("TotalMoney"));
                payment.setSearchCount(rs.getInt("SearchCount"));
                payment.setDescription(rs.getString("Description"));
                payment.setStatus(rs.getString("Status"));
                
                payments.add(payment);
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return payments;
    }
    
    public List<Payment> getPaymentsByStatus(String status) {
        List<Payment> payments = new ArrayList<>();
        try {
            String query = queryString + " WHERE Status = ?";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, status);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentID(rs.getInt("PaymentID"));
                payment.setOrderID(rs.getInt("OrderID"));
                payment.setCustomerID(rs.getInt("CustomerID"));
                payment.setName(rs.getString("Name"));
                payment.setPaymentType(rs.getString("PaymentType"));
                payment.setShippingFee(rs.getBigDecimal("ShippingFee"));
                
                // Convert SQL Timestamp to LocalDateTime
                Timestamp timestamp = rs.getTimestamp("PaymentDate");
                payment.setPaymentDate(timestamp != null ? timestamp.toLocalDateTime() : null);
                
                payment.setTotalMoney(rs.getBigDecimal("TotalMoney"));
                payment.setSearchCount(rs.getInt("SearchCount"));
                payment.setDescription(rs.getString("Description"));
                payment.setStatus(rs.getString("Status"));
                
                payments.add(payment);
            }
            
            rs.close();
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return payments;
    }
}
