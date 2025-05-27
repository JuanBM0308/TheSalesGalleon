package com.juanba.the_sales_galleon.product.controller;

import com.juanba.the_sales_galleon.product.dto.ProductDto;
import com.juanba.the_sales_galleon.product.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/api/list-products")
    public ResponseEntity<?> getAll() {
        return productService.getAll();
    }

    @GetMapping("/api/find-product/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/api/active-products")
    public ResponseEntity<?> getActiveProducts() {
        return productService.findByActive();
    }

    @PostMapping("/api/create")
    public ResponseEntity<?> create(@RequestBody @Valid ProductDto productDto) {
        return productService.create(productDto);
    }

    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    @PutMapping("/api/update")
    public ResponseEntity<?> update(@RequestBody @Valid ProductDto productDto) {
        return productService.update(productDto);
    }
}
