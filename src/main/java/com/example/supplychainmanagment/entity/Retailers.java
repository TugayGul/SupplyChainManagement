package com.example.supplychainmanagment.entity;

import javax.persistence.*;

@Entity
@Table(name = "retailers")
public class Retailers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "emailadress")
    private String emailAdress;

    @Column(name = "password")
    private String password;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmailAdress() { return emailAdress; }
    public void setEmailAdress(String emailAdress) { this.emailAdress = emailAdress; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}