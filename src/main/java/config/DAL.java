package config;

import java.sql.*;

public class DAL {
    private Connection conn;
    private Statement stsm;

    public DAL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Đổi tên driver
            conn = DriverManager.getConnection("jdbc:mysql://localhost/laptopshop", "root", "123456");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public ResultSet getData(String sql) {
        try {
            stsm = conn.createStatement();
            return stsm.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateData(String sql) {
        try {
            stsm = conn.createStatement();
            return stsm.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void closeConnection() {
        try {
            if (stsm != null) stsm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
