package com.supplychainmanagment.dao;

import com.supplychainmanagment.db.DBConnection;
import com.supplychainmanagment.entity.SupplierPrincipal;
import com.supplychainmanagment.entity.Suppliers;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO implements UserDetailsService {
    private DBConnection dbConnection;
    private Connection connection;

    public SupplierDAO() {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    public List<Suppliers> getAllSuppliers() {
        List<Suppliers> suppliers = new ArrayList<>();
        try {
            String sql = "SELECT id, userid, name, emailadress, phonenumber, password FROM suppliers";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Suppliers supplier = new Suppliers();
                supplier.setId(resultSet.getInt("id"));
                supplier.setUserid(resultSet.getInt("userid"));
                supplier.setName(resultSet.getString("name"));
                supplier.setEmailAdress(resultSet.getString("emailadress"));
                supplier.setPhoneNumber(resultSet.getString("phonenumber"));
                supplier.setPassword(resultSet.getString("password"));
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
            String sql = "SELECT * FROM suppliers WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                supplier.setId(resultSet.getInt("id"));
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
            String sql = "INSERT INTO suppliers (id, emailadress, phonenumber, password) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, supplier.getId());
            statement.setString(2, supplier.getEmailAdress());
            statement.setString(3, supplier.getPhoneNumber());
            statement.setString(4, supplier.getPassword());
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSupplier(Suppliers supplier) {
        try {
            String sql = "UPDATE suppliers SET emailadress = ?, phonenumber = ?, password = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, supplier.getEmailAdress());
            statement.setString(2, supplier.getPhoneNumber());
            statement.setString(3, supplier.getPassword());
            statement.setInt(4, supplier.getId());
            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public SupplierPrincipal loadUserByUsername(String email) {
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
                return new SupplierPrincipal(id, name, phoneNumber, emailAdress, password);
            } else {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }
        } catch (SQLException e) {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }
    }
}
