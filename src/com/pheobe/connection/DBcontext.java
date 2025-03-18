package com.pheobe.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBcontext {

    public static String USERNAME = "sa";
    public static String PASWORD = "1234";
    public static String URL = "jdbc:sqlserver://localhost:1433;databaseName=PRO230_DATN_v2;encrypt=true;trustServerCertificate=true;";
    
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection(){
        Connection cn=null;
        try {
            cn = DriverManager.getConnection(URL, USERNAME, PASWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cn;
    }

    public static void main(String[] args) {
        Connection cn = getConnection();
        if(cn!=null){
            System.out.println("Kết nối thành công");
        }
    }

}
