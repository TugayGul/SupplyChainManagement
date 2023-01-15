package com.supplychainmanagment.controller;

com.supplychainmanagment.controller;

import com.supplychainmanagment.dao.SupplierDAO;
import com.supplychainmanagment.entity.Suppliers;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierDAO supplierDAO;

    public SupplierController(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    @GetMapping
    public List<Suppliers> getAllSuppliers() {
        return supplierDAO.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Suppliers getSupplierById(@PathVariable int id) {
        return supplierDAO.getSupplierById(id);
    }

    @PostMapping
    public boolean addSupplier(@RequestBody Suppliers supplier) {
        return supplierDAO.addSupplier(supplier);
    }

    @PutMapping("/{id}")
    public boolean updateSupplier(@PathVariable int id, @RequestBody Suppliers supplier) {
        supplier.setId(id);
        return supplierDAO.updateSupplier(supplier);
    }

}