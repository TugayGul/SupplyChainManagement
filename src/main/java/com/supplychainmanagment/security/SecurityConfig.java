package com.supplychainmanagment.security;

import com.supplychainmanagment.dao.RetailerDAO;
import com.supplychainmanagment.dao.SupplierDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.supplychainmanagment.dao"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final RetailerDAO retailerDAO;
    private final SupplierDAO supplierDAO;

    @Autowired
    public SecurityConfig(RetailerDAO retailerDAO, SupplierDAO supplierDAO) {
        this.retailerDAO = retailerDAO;
        this.supplierDAO = supplierDAO;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(retailerDAO)
                .and()
                .userDetailsService(supplierDAO);
    }
}