package com.example.supplychainmanagment.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "productname")
    private String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private float price;

    // Getters and setters for each field
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

}