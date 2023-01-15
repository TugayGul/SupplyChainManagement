package com.example.supplychainmanagment.db;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
public class DBConnection {
    private Connection conn;

    public DBConnection() {
        try {
            Properties prop = new Properties();
            InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("config/db.properties");
            prop.load(input);

            String url = "jdbc:mysql://" + prop.getProperty("hostname") + ":" + prop.getProperty("port") + "/" + prop.getProperty("db_name");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return conn;
    }

    public ResultSet select(String sql) {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(String sql) {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(String sql) {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String sql) {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}