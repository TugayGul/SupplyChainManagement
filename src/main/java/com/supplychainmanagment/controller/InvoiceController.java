package com.supplychainmanagment.controller;

import com.supplychainmanagment.dao.InvoiceDAO;
import com.supplychainmanagment.entity.Invoices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private InvoiceDAO invoiceDAO;

    public InvoiceController() {
        invoiceDAO = new InvoiceDAO();
    }

    @GetMapping
    public List<Invoices> getAllInvoices() {
        return invoiceDAO.getAllInvoices();
    }

    @GetMapping("/{id}")
    public Invoices getInvoiceById(@PathVariable int id) {
        return invoiceDAO.getInvoiceById(id);
    }

    @PostMapping
    public boolean addInvoice(@RequestBody Invoices invoice) {
        return invoiceDAO.addInvoice(invoice);
    }

}