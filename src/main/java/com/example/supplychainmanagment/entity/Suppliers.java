package com.example.supplychainmanagment.entity;

import javax.persistence.*;

@Entity
@Table(name = "suppliers")
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    @Column(name = "name")
    private String name;

    @Column(name = "emailadress")
    private String emailAdress;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    public int getUserid() { return userid; }
    public void setUserid(int userid) { this.userid = userid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmailAdress() { return emailAdress; }
    public void setEmailAdress(String emailAdress) { this.emailAdress = emailAdress; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}