package com.supplychainmanagment.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class RetailerPrincipal implements UserDetails {
    private int id;
    private String name;
    private String phoneNumber;
    private String emailAdress;
    private String password;

    public RetailerPrincipal(int id, String name, String phoneNumber, String emailAdress, String password) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;
        this.password = password;
    }
    private Retailers retailer;

    public RetailerPrincipal(Retailers retailer) {
        this.retailer = retailer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return retailer.getPassword();
    }

    @Override
    public String getUsername() {
        return retailer.getEmailAdress();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
