package com.supplychainmanagment.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.supplychainmanagment.dao.ProductDAO;
import com.supplychainmanagment.entity.Product;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productDAO.getProductById(id);
    }

    @PostMapping
    public boolean addProduct(@RequestBody Product product) {
        return productDAO.addProduct(product);
    }

    @PutMapping
    public boolean updateProduct(@RequestBody Product product) {
        return productDAO.updateProduct(product);
    }

}