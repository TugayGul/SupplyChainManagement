package com.supplychainmanagment.service;

import com.supplychainmanagment.dao.RetailerDAO;
import com.supplychainmanagment.dao.SupplierDAO;
import com.supplychainmanagment.entity.RetailerPrincipal;
import com.supplychainmanagment.entity.SupplierPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SupplierDAO supplierDAO;
    @Autowired
    private RetailerDAO retailerDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SupplierPrincipal supplier = supplierDAO.loadUserByUsername(email);
        if (supplier != null) {
            return supplier;
        } else {
            RetailerPrincipal retailer = retailerDAO.loadUserByUsername(email);
            if (retailer != null) {
                return retailer;
            } else {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }
        }
    }
}