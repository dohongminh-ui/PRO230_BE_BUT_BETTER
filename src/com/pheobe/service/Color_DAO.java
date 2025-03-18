/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.service;
import com.pheobe.model.Color;
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
public class Color_DAO {
    private String queryToSelectAll = "SELECT * FROM Color";
    private String queryToSelectByID = "SELECT * FROM Color WHERE colorId = ?";
    private String queryToInsert = "INSERT INTO Color (name, hexCode, image, createOrUpdateDate) VALUES (?, ?, ?, ?)";
    private String queryToUpdate = "UPDATE Color SET name = ?, hexCode = ?, image = ?, createOrUpdateDate = ? WHERE colorId = ?";
    private String queryToDelete = "DELETE FROM Color WHERE colorId = ?";
    
    private Connection con = DBcontext.getConnection();
    
    public ArrayList<Color> selectAll() {
        ArrayList<Color> colors = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectAll);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                Color color = new Color();
                color.setColorId(rs.getInt("colorId"));
                color.setName(rs.getString("name"));
                color.setHexCode(rs.getString("hexCode"));
                color.setImage(rs.getBytes("image"));
                color.setCreateOrUpdateDate(rs.getObject("createOrUpdateDate", LocalDateTime.class));
                colors.add(color);
            }
            rs.close();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colors;
    }
    
    public Color selectById(int id) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectByID);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            
            if (rs.next()) {
                Color color = new Color();
                color.setColorId(rs.getInt("colorId"));
                color.setName(rs.getString("name"));
                color.setHexCode(rs.getString("hexCode"));
                color.setImage(rs.getBytes("image"));
                color.setCreateOrUpdateDate(rs.getObject("createOrUpdateDate", LocalDateTime.class));
                rs.close();
                pre.close();
                return color;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean insert(Color color) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToInsert);
            pre.setString(1, color.getName());
            pre.setString(2, color.getHexCode());
            pre.setBytes(3, color.getImage());
            pre.setObject(4, color.getCreateOrUpdateDate());
            
            int row = pre.executeUpdate();
            pre.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(Color color) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToUpdate);
            pre.setString(1, color.getName());
            pre.setString(2, color.getHexCode());
            pre.setBytes(3, color.getImage());
            pre.setObject(4, color.getCreateOrUpdateDate());
            pre.setInt(5, color.getColorId());
            
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