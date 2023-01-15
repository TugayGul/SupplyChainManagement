package com.supplychainmanagment.controller;
import com.supplychainmanagment.dao.RetailerDAO;
import com.supplychainmanagment.entity.Retailers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retailers")
public class RetailerController {

    @Autowired
    private RetailerDAO retailerDAO;

    @GetMapping("/all")
    public List<Retailers> getAllRetailers() {
        return retailerDAO.getAllRetailers();
    }

    @GetMapping("/{id}")
    public Retailers getRetailerById(@PathVariable int id) {
        return retailerDAO.getRetailerById(id);
    }

    @PostMapping("/add")
    public boolean addRetailer(@RequestBody Retailers retailer) {
        return retailerDAO.addRetailer(retailer);
    }

}