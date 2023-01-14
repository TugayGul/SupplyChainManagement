package com.example.supplychainmanagment.db;

import java.sql.ResultSet;

public class DBConnectionTest {
    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        String sql = "SELECT * FROM products";
        ResultSet rs = db.select(sql);
        try {
            while (rs.next()) {
                System.out.println(rs.getString("productname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }
}