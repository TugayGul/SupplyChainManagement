package com.example.supplychainmanagment.dao;

import com.example.supplychainmanagment.db.DBConnection;
import com.example.supplychainmanagment.entity.Invoices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {
    private DBConnection dbConnection;
    private Connection connection;

    public InvoiceDAO() {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    public List<Invoices> getAllInvoices() {
        List<Invoices> invoices = new ArrayList<>();
        try {
            String sql = "SELECT * FROM invoices";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Invoices invoice = new Invoices();
                invoice.setBillId(resultSet.getInt("billid"));
                invoice.setProductId(resultSet.getInt("productid"));
                invoice.setTotalAmount(resultSet.getDouble("totalamount"));
                invoice.setProductName(resultSet.getString("productname"));
                invoice.setProductPrice(resultSet.getDouble("productprice"));
                invoice.setProductQuantity(resultSet.getInt("productquantity"));
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    public Invoices getInvoiceById(int id) {
        Invoices invoice = new Invoices();
        try {
            String sql = "SELECT * FROM invoices WHERE billid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                invoice.setBillId(resultSet.getInt("billid"));
                invoice.setProductId(resultSet.getInt("productid"));
                invoice.setTotalAmount(resultSet.getDouble("totalamount"));
                invoice.setProductName(resultSet.getString("productname"));
                invoice.setProductPrice(resultSet.getDouble("productprice"));
                invoice.setProductQuantity(resultSet.getInt("productquantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    public boolean addInvoice(Invoices invoice) {
        try {
            String sql = "INSERT INTO invoices (productid, totalamount, productname, productprice, productquantity) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, invoice.getProductId());
            statement.setDouble(2, invoice.getTotalAmount());
            statement.setString(3, invoice.getProductName());
            statement.setDouble(4, invoice.getProductPrice());
            statement.setInt(5, invoice.getProductQuantity());

            int result = statement.executeUpdate();
            if (result > 0) {
                //update product quantity
                String updateSQL = "UPDATE products SET quantity = quantity - ? WHERE productid = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
                updateStatement.setInt(1, invoice.getProductQuantity());
                updateStatement.setInt(2, invoice.getProductId());
                updateStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}