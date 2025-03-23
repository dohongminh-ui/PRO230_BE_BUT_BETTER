/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pheobe.service;

import com.pheobe.model.Cart_detail;
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
public class Cart_Detail_DAO {

    private String queryToSelectAll = "SELECT * FROM Cart_detail";
    private String queryToSelectByID = "SELECT * FROM Cart_detail WHERE cartDetailID = ?";
    private String queryToInsert = "INSERT INTO Cart_detail (cartID, productId, price, quantity, status, createDate, productColorId) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private String queryToUpdate = "UPDATE Cart_detail SET cartID = ?, productId = ?, price = ?, quantity = ?, status = ?, productColorId = ? WHERE cartDetailID = ?";
    private String queryToDelete = "DELETE FROM Cart_detail WHERE ProductId = ?";

    private Connection con = DBcontext.getConnection();

    public ArrayList<Cart_detail> selectAll() {
        ArrayList<Cart_detail> cartDetails = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectAll);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                Cart_detail cartDetail = new Cart_detail();
                cartDetail.setCartDetailID(rs.getInt("cartDetailID"));
                cartDetail.setCartID(rs.getInt("cartID"));
                cartDetail.setProductId(rs.getInt("productId"));
                cartDetail.setPrice(rs.getBigDecimal("price"));
                cartDetail.setQuantity(rs.getInt("quantity"));
                cartDetail.setStatus(rs.getString("status"));
                cartDetail.setCreateDate(rs.getObject("createDate", LocalDateTime.class));
                cartDetail.setProductColorId(rs.getInt("productColorId"));
                cartDetails.add(cartDetail);
            }
            rs.close();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartDetails;
    }

    public ArrayList<Cart_detail> selectAllName() {
        ArrayList<Cart_detail> cartDetails = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement("SELECT p.Name FROM CART_DETAIL cd\n"
                    + "INNER JOIN PRODUCT p \n"
                    + "ON p.IdProduct = cd.CartID\n"
                    + "WHERE cd.CartID = p.IdProduct");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Cart_detail cartDetail = new Cart_detail();
            }
            rs.close();
            pre.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartDetails;
    }

    public Cart_detail selectById(int id) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToSelectByID);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                Cart_detail cartDetail = new Cart_detail();
                cartDetail.setCartDetailID(rs.getInt("cartDetailID"));
                cartDetail.setCartID(rs.getInt("cartID"));
                cartDetail.setProductId(rs.getInt("productId"));
                cartDetail.setPrice(rs.getBigDecimal("price"));
                cartDetail.setQuantity(rs.getInt("quantity"));
                cartDetail.setStatus(rs.getString("status"));
                cartDetail.setCreateDate(rs.getObject("createDate", LocalDateTime.class));
                cartDetail.setProductColorId(rs.getInt("productColorId"));
                rs.close();
                pre.close();
                return cartDetail;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(Cart_detail cartDetail) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToInsert);
            pre.setInt(1, cartDetail.getCartID());
            pre.setInt(2, cartDetail.getProductId());
            pre.setBigDecimal(3, cartDetail.getPrice());
            pre.setInt(4, cartDetail.getQuantity());
            pre.setString(5, cartDetail.getStatus());
            pre.setObject(6, cartDetail.getCreateDate());
            pre.setObject(7, cartDetail.getProductColorId());

            int row = pre.executeUpdate();
            pre.close();
            return row > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Cart_detail cartDetail) {
        try {
            PreparedStatement pre = con.prepareStatement(queryToUpdate);
            pre.setInt(1, cartDetail.getCartID());
            pre.setInt(2, cartDetail.getProductId());
            pre.setBigDecimal(3, cartDetail.getPrice());
            pre.setInt(4, cartDetail.getQuantity());
            pre.setString(5, cartDetail.getStatus());
            pre.setObject(6, cartDetail.getProductColorId());
            pre.setInt(7, cartDetail.getCartDetailID());

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
