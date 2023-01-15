package com.supplychainmanagment.controller;
import com.supplychainmanagment.dao.RetailerDAO;
import com.supplychainmanagment.entity.Retailers;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retailers")
public class RetailerController {
    private String email;
    private String password;
    private HttpServletRequest request;

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {
        this.email = email;
        this.password = password;
        this.request = request;
        // authenticate the user and set their details in the session
        // ...
        return "redirect:/dashboard";
    }
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