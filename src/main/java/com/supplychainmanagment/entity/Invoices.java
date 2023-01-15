package com.supplychainmanagment.entity;

import javax.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billId;

    @Column(name = "productid")
    private int productId;

    @Column(name = "totalamount")
    private double totalAmount;

    @Column(name = "productname")
    private String productName;

    @Column(name = "productprice")
    private double productPrice;

    @Column(name = "productquantity")
    private int productQuantity;

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public double getProductPrice() { return productPrice; }
    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }
    public int getProductQuantity() { return productQuantity; }
    public void setProductQuantity(int productQuantity) { this.productQuantity = productQuantity; }

}