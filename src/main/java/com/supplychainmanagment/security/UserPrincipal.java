package com.supplychainmanagment.security;
import com.supplychainmanagment.entity.Retailers;
import com.supplychainmanagment.entity.Suppliers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
public class UserPrincipal implements UserDetails {
    private long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    public UserPrincipal(long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserPrincipal create(Retailers retailer) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_RETAILER"));
        return new UserPrincipal(retailer.getId(), retailer.getEmailAdress(), retailer.getPassword(), authorities);
    }

    public static UserPrincipal create(Suppliers supplier) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_SUPPLIER"));
        return new UserPrincipal(supplier.getId(), supplier.getEmailAdress(), supplier.getPassword(), authorities);
    }
}