package com.supplychainmanagment.dao;

import com.supplychainmanagment.db.DBConnection;
import com.supplychainmanagment.entity.Retailers;
import com.supplychainmanagment.entity.Suppliers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    private DBConnection dbConnection;
    private Connection connection;

    public SupplierDAO() {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    public List<Suppliers> getAllSuppliers() {
        List<Suppliers> suppliers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM suppliers";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Suppliers supplier = new Suppliers();
                supplier.setUserid(resultSet.getInt("userid"));
                supplier.setName(resultSet.getString("name"));
                supplier.setEmailAdress(resultSet.getString("emailadress"));
                supplier.setPhoneNumber(resultSet.getString("phonenumber"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public Suppliers getSupplierById(int id) {
        Suppliers supplier = new Suppliers();
        try {
            String sql = "SELECT * FROM suppliers WHERE supplierid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                supplier.setUserid(resultSet.getInt("userid"));
                supplier.setName(resultSet.getString("name"));
                supplier.setEmailAdress(resultSet.getString("emailadress"));
                supplier.setPhoneNumber(resultSet.getString("phonenumber"));
                supplier.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

    public boolean addSupplier(Suppliers supplier) {
        try {
            String sql = "INSERT INTO suppliers (userid, password, name, emailadress, phonenumber) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, supplier.getUserid());
            statement.setString(2, supplier.getPassword());
            statement.setString(3, supplier.getName());
            statement.setString(4, supplier.getEmailAdress());
            statement.setString(5, supplier.getPhoneNumber());
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
    public boolean updateSupplier(Suppliers supplier) {
        try {
            String sql = "UPDATE suppliers SET userid = ?, password = ?, name = ?, emailadress = ?, phonenumber = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, supplier.getUserid());
            stmt.setString(2, supplier.getPassword());
            stmt.setString(3, supplier.getName());
            stmt.setString(4, supplier.getEmailAdress());
            stmt.setString(5, supplier.getPhoneNumber());
            stmt.setInt(6, supplier.getId());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}