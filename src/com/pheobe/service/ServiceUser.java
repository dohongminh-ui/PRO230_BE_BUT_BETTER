package com.pheobe.service;

import com.pheobe.connection.DBcontext;
import com.pheobe.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.time.LocalDateTime;

public class ServiceUser {

    private final Connection con;

    public ServiceUser() {
        con = DBcontext.getConnection();
    }

    public Customer login(Customer login) throws SQLException {
        Customer data = null;
        PreparedStatement p = con.prepareStatement("SELECT IdCustomer, UserName, Email, isAdmin FROM CUSTOMER WHERE Email=? AND Password=?");
        p.setString(1, login.getEmail());
        p.setString(2, login.getPassword());
        ResultSet r = p.executeQuery();
        if (r.next()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String email = r.getString(3);
            boolean isAdmin = r.getBoolean(4);
            data = new Customer(userID, email, "", userName);
            data.setAdmin(isAdmin);
            updateLastLogin(userID);
        }
        r.close();
        p.close();
        return data;
    }

    private void updateLastLogin(int userId) throws SQLException {
        PreparedStatement p = con.prepareStatement("UPDATE CUSTOMER SET logOut = ?, accountFailCount = 0 WHERE IdCustomer = ?");
        p.setObject(1, LocalDateTime.now());
        p.setInt(2, userId);
        p.executeUpdate();
        p.close();
    }

    public void insertUser(Customer user) throws SQLException {
        PreparedStatement p = con.prepareStatement("INSERT into CUSTOMER(UserName, Email, [Password],Name)\n"
                + "VALUES(?,?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);
        p.setString(1, "UNKNOWN");
        p.setString(2, user.getEmail());
        p.setString(3, user.getPassword());
        p.setString(1, user.getUserName());
        p.execute();
        ResultSet r = p.getGeneratedKeys();
        while (r.next()) {
            int userID = r.getInt(1);
            user.setIdCustomer(userID);
        }
        r.close();
        p.close();
    }

    public boolean checkDuplicateUser(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("SELECT IdCustomer FROM CUSTOMER WHERE UserName=? AND Status='Active'");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    public boolean checkDuplicateEmail(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("SELECT IdCustomer FROM CUSTOMER WHERE Email=? AND Status='Active'");
        p.setString(1, user);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            duplicate = true;
        }
        r.close();
        p.close();
        return duplicate;
    }

    // admin

    public void makeAdmin(int userId, boolean isAdmin) throws SQLException {
        PreparedStatement p = con.prepareStatement("UPDATE CUSTOMER SET isAdmin=? WHERE IdCustomer=?");
        p.setBoolean(1, isAdmin);
        p.setInt(2, userId);
        p.execute();
        p.close();
    }
}
