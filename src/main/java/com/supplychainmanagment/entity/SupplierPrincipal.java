package com.supplychainmanagment.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class SupplierPrincipal implements UserDetails {
    private int id;
    private String name;
    private String phoneNumber;
    private String emailAdress;
    private String password;

    public SupplierPrincipal(int id, String name, String phoneNumber, String emailAdress, String password) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;
        this.password = password;
    }
    private Suppliers supplier;

    public SupplierPrincipal(Suppliers supplier) {
        this.supplier = supplier;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_SUPPLIER"));
    }

    @Override
    public String getPassword() {
        return supplier.getPassword();
    }

    @Override
    public String getUsername() {
        return supplier.getEmailAdress();
    }

    public int getId() {
        return supplier.getId();
    }

    public String getName() {
        return supplier.getName();
    }

    public String getEmailAdress() {
        return supplier.getEmailAdress();
    }

    public String getPhoneNumber() {
        return supplier.getPhoneNumber();
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
