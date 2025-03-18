/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.service;
import com.pheobe.model.Evaluate;
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
public class Evaluate_DAO {
    private static String getAllEvaluates = "SELECT * FROM evaluate";
    private static String getEvaluateById = "SELECT * FROM evaluate WHERE id = ?";
    private static String getEvaluatesByProductId = "SELECT * FROM evaluate WHERE product_id = ?";
    private static String getEvaluatesByCustomerId = "SELECT * FROM evaluate WHERE customer_id = ?";
    private static String insertEvaluate = "INSERT INTO evaluate (product_id, customer_id, comment, rating, date) VALUES (?, ?, ?, ?, ?)";
    private static String updateEvaluate = "UPDATE evaluate SET product_id = ?, customer_id = ?, comment = ?, rating = ?, update_date = ? WHERE id = ?";
    private static String deleteEvaluate = "DELETE FROM evaluate WHERE id = ?";
    
    Connection con = DBcontext.getConnection();
    
    public List<Evaluate> getAllEvaluates() {
        List<Evaluate> evaluates = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getAllEvaluates);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Evaluate evaluate = new Evaluate();
                evaluate.setId(rs.getInt("id"));
                evaluate.setProductId(rs.getInt("product_id"));
                evaluate.setCustomerId(rs.getInt("customer_id"));
                evaluate.setComment(rs.getString("comment"));
                evaluate.setRating(rs.getObject("rating", Integer.class));
                evaluate.setDate(rs.getObject("date", LocalDateTime.class));
                evaluate.setUpdateDate(rs.getObject("update_date", LocalDateTime.class));
                evaluates.add(evaluate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluates;
    }
    
    public Evaluate getEvaluateById(int id) {
        try {
            PreparedStatement statement = con.prepareStatement(getEvaluateById);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                Evaluate evaluate = new Evaluate();
                evaluate.setId(rs.getInt("id"));
                evaluate.setProductId(rs.getInt("product_id"));
                evaluate.setCustomerId(rs.getInt("customer_id"));
                evaluate.setComment(rs.getString("comment"));
                evaluate.setRating(rs.getObject("rating", Integer.class));
                evaluate.setDate(rs.getObject("date", LocalDateTime.class));
                evaluate.setUpdateDate(rs.getObject("update_date", LocalDateTime.class));
                return evaluate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Evaluate> getEvaluatesByProductId(int productId) {
        List<Evaluate> evaluates = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getEvaluatesByProductId);
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Evaluate evaluate = new Evaluate();
                evaluate.setId(rs.getInt("id"));
                evaluate.setProductId(rs.getInt("product_id"));
                evaluate.setCustomerId(rs.getInt("customer_id"));
                evaluate.setComment(rs.getString("comment"));
                evaluate.setRating(rs.getObject("rating", Integer.class));
                evaluate.setDate(rs.getObject("date", LocalDateTime.class));
                evaluate.setUpdateDate(rs.getObject("update_date", LocalDateTime.class));
                evaluates.add(evaluate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluates;
    }
    
    public List<Evaluate> getEvaluatesByCustomerId(int customerId) {
        List<Evaluate> evaluates = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getEvaluatesByCustomerId);
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Evaluate evaluate = new Evaluate();
                evaluate.setId(rs.getInt("id"));
                evaluate.setProductId(rs.getInt("product_id"));
                evaluate.setCustomerId(rs.getInt("customer_id"));
                evaluate.setComment(rs.getString("comment"));
                evaluate.setRating(rs.getObject("rating", Integer.class));
                evaluate.setDate(rs.getObject("date", LocalDateTime.class));
                evaluate.setUpdateDate(rs.getObject("update_date", LocalDateTime.class));
                evaluates.add(evaluate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluates;
    }
    
    public boolean insertEvaluate(Evaluate evaluate) {
        try {
            PreparedStatement statement = con.prepareStatement(insertEvaluate);
            statement.setInt(1, evaluate.getProductId());
            statement.setInt(2, evaluate.getCustomerId());
            statement.setString(3, evaluate.getComment());
            statement.setObject(4, evaluate.getRating());
            statement.setObject(5, evaluate.getDate());
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateEvaluate(Evaluate evaluate) {
        try {
            PreparedStatement statement = con.prepareStatement(updateEvaluate);
            statement.setInt(1, evaluate.getProductId());
            statement.setInt(2, evaluate.getCustomerId());
            statement.setString(3, evaluate.getComment());
            statement.setObject(4, evaluate.getRating());
            statement.setObject(5, LocalDateTime.now());
            statement.setInt(6, evaluate.getId());
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteEvaluate(int id) {
        try {
            PreparedStatement statement = con.prepareStatement(deleteEvaluate);
            statement.setInt(1, id);
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}