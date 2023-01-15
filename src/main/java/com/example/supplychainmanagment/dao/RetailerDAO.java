package com.example.supplychainmanagment.dao;

import com.example.supplychainmanagment.db.DBConnection;
import com.example.supplychainmanagment.entity.Retailers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetailerDAO {
    private DBConnection dbConnection;
    private Connection connection;

    public RetailerDAO() {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    public List<Retailers> getAllRetailers() {
        List<Retailers> retailers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM retailers";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Retailers retailer = new Retailers();
                retailer.setId(resultSet.getInt("id"));
                retailer.setName(resultSet.getString("name"));
                retailer.setEmailAdress(resultSet.getString("emailadress"));
                retailer.setPhoneNumber(resultSet.getString("phonenumber"));
                retailers.add(retailer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retailers;
    }

    public Retailers getRetailerById(int id) {
        Retailers retailer = new Retailers();
        try {
            String sql = "SELECT * FROM retailers WHERE userid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                retailer.setId(resultSet.getInt("id"));
                retailer.setName(resultSet.getString("name"));
                retailer.setPhoneNumber(resultSet.getString("phonenumber"));
                retailer.setEmailAdress(resultSet.getString("emailadress"));
                retailer.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retailer;
    }

    public boolean addRetailer(Retailers retailer) {
        try {
            String sql = "INSERT INTO retailers (name, phonenumber, emailaddress, password) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, retailer.getName());
            statement.setString(2, retailer.getPhoneNumber());
            statement.setString(3, retailer.getEmailAdress());
            statement.setString(4, retailer.getPassword());

            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateRetailer(Retailers retailer) {
        try {
            String sql = "UPDATE retailers SET name = ?, phonenumber = ?, emailaddress = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, retailer.getName());
            stmt.setString(2, retailer.getPhoneNumber());
            stmt.setString(3, retailer.getEmailAdress());
            stmt.setInt(4, retailer.getId());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
