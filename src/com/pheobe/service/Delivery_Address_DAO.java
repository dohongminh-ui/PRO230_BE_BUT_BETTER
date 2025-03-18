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
/**
 *
 * @author Admin
 */
import com.pheobe.model.Delivery_Address;
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
public class Delivery_Address_DAO {
    private static String getAllAddress = "SELECT * FROM delivery_address";
    private static String getAddressById = "SELECT * FROM delivery_address WHERE id = ?";
    private static String getAddressByCustomerId = "SELECT * FROM delivery_address WHERE customer_id = ?";
    private static String insertAddress = "INSERT INTO delivery_address (customer_id, name, address, phone_number, is_default, create_date) VALUES (?, ?, ?, ?, ?, ?)";
    private static String updateAddress = "UPDATE delivery_address SET customer_id = ?, name = ?, address = ?, phone_number = ?, is_default = ?, update_date = ? WHERE id = ?";
    private static String deleteAddress = "DELETE FROM delivery_address WHERE id = ?";
    
    Connection con = DBcontext.getConnection();
    
    
    public List<Delivery_Address> getAllDeliveryAddresses() {
        List<Delivery_Address> addresses = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getAllAddress);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Delivery_Address address = new Delivery_Address();
                address.setId(rs.getInt("id"));
                address.setCustomerId(rs.getInt("customer_id"));
                address.setName(rs.getString("name"));
                address.setAddress(rs.getString("address"));
                address.setPhoneNumber(rs.getString("phone_number"));
                address.setDefault(rs.getBoolean("is_default"));
                address.setCreateDate(rs.getObject("create_date", LocalDateTime.class));
                address.setUpdateDate(rs.getObject("update_date", LocalDateTime.class));
                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }
    
    public Delivery_Address getDeliveryAddressById(int id) {
        try {
            PreparedStatement statement = con.prepareStatement(getAddressById);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                Delivery_Address address = new Delivery_Address();
                address.setId(rs.getInt("id"));
                address.setCustomerId(rs.getInt("customer_id"));
                address.setName(rs.getString("name"));
                address.setAddress(rs.getString("address"));
                address.setPhoneNumber(rs.getString("phone_number"));
                address.setDefault(rs.getBoolean("is_default"));
                address.setCreateDate(rs.getObject("create_date", LocalDateTime.class));
                address.setUpdateDate(rs.getObject("update_date", LocalDateTime.class));
                return address;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Delivery_Address> getDeliveryAddressesByCustomerId(int customerId) {
        List<Delivery_Address> addresses = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getAddressByCustomerId);
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Delivery_Address address = new Delivery_Address();
                address.setId(rs.getInt("id"));
                address.setCustomerId(rs.getInt("customer_id"));
                address.setName(rs.getString("name"));
                address.setAddress(rs.getString("address"));
                address.setPhoneNumber(rs.getString("phone_number"));
                address.setDefault(rs.getBoolean("is_default"));
                address.setCreateDate(rs.getObject("create_date", LocalDateTime.class));
                address.setUpdateDate(rs.getObject("update_date", LocalDateTime.class));
                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }
    
    public boolean insertDeliveryAddress(Delivery_Address address) {
        try {
            PreparedStatement statement = con.prepareStatement(insertAddress);
            statement.setInt(1, address.getCustomerId());
            statement.setString(2, address.getName());
            statement.setString(3, address.getAddress());
            statement.setString(4, address.getPhoneNumber());
            statement.setBoolean(5, address.isDefault());
            statement.setObject(6, address.getCreateDate());
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateDeliveryAddress(Delivery_Address address) {
        try {
            PreparedStatement statement = con.prepareStatement(updateAddress);
            statement.setInt(1, address.getCustomerId());
            statement.setString(2, address.getName());
            statement.setString(3, address.getAddress());
            statement.setString(4, address.getPhoneNumber());
            statement.setBoolean(5, address.isDefault());
            statement.setObject(6, LocalDateTime.now());
            statement.setInt(7, address.getId());
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteDeliveryAddress(int id) {
        try {
            PreparedStatement statement = con.prepareStatement(deleteAddress);
            statement.setInt(1, id);
            
            int row = statement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
