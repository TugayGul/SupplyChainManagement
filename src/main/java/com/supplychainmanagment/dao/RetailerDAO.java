package com.supplychainmanagment.dao;

import com.supplychainmanagment.db.DBConnection;
import com.supplychainmanagment.entity.RetailerPrincipal;
import com.supplychainmanagment.entity.Retailers;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetailerDAO implements UserDetailsService {
    private DBConnection dbConnection;
    private Connection connection;

    public RetailerDAO() {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    public List<Retailers> getAllRetailers() {
        List<Retailers> retailers = new ArrayList<>();
        try {
            String sql = "SELECT id, name, emailadress, phonenumber, password FROM retailers";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Retailers retailer = new Retailers();
                retailer.setId(resultSet.getInt("id"));
                retailer.setName(resultSet.getString("name"));
                retailer.setEmailAdress(resultSet.getString("emailadress"));
                retailer.setPhoneNumber(resultSet.getString("phonenumber"));
                retailer.setPassword(resultSet.getString("password"));
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
            String sql = "SELECT * FROM retailers WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                retailer.setId(resultSet.getInt("id"));
                retailer.setName(resultSet.getString("name"));
                retailer.setEmailAdress(resultSet.getString("emailadress"));
                retailer.setPhoneNumber(resultSet.getString("phonenumber"));
                retailer.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retailer;
    }
    public boolean addRetailer(Retailers retailer) {
        try {
            String sql = "INSERT INTO retailers (id, emailadress, phonenumber, password) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, retailer.getId());
            statement.setString(2, retailer.getEmailAdress());
            statement.setString(3, retailer.getPhoneNumber());
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
            String sql = "UPDATE retailers SET emailadress = ?, phonenumber = ?, password = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, retailer.getEmailAdress());
            statement.setString(2, retailer.getPhoneNumber());
            statement.setString(3, retailer.getPassword());
            statement.setInt(4, retailer.getId());
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public RetailerPrincipal loadUserByUsername(String email) {
        try {
            String sql = "SELECT * FROM retailers WHERE emailadress = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phonenumber");
                String emailAdress = resultSet.getString("emailadress");
                String password = resultSet.getString("password");
                return new RetailerPrincipal(id, name, phoneNumber, emailAdress, password);
            } else {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }
        } catch (SQLException e) {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }
    }
}