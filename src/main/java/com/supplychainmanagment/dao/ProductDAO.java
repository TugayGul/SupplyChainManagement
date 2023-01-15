package com.supplychainmanagment.dao;

import com.supplychainmanagment.db.DBConnection;
import com.supplychainmanagment.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private DBConnection dbConnection;
    private Connection connection;

    public ProductDAO() {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM products";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("productid"));
                product.setProductName(resultSet.getString("productname"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setPrice(resultSet.getFloat("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(int id) {
        Product product = new Product();
        try {
            String sql = "SELECT * FROM products WHERE productid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                product.setProductId(resultSet.getInt("productid"));
                product.setProductName(resultSet.getString("productname"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setPrice(resultSet.getFloat("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean addProduct(Product product) {
        try {
            String sql = "INSERT INTO products (productname, quantity, price) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getQuantity());
            statement.setFloat(3, product.getPrice());

            int result = statement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateProduct(Product product) {
        try {
            String sql = "UPDATE products SET productname = ?, quantity = ?, price = ? WHERE productid = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getQuantity());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getProductId());
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}