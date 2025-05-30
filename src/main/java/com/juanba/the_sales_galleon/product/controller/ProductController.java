package com.juanba.the_sales_galleon.product.controller;

import com.juanba.the_sales_galleon.product.dto.ProductDto;
import com.juanba.the_sales_galleon.product.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/api")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/list-products")
    public ResponseEntity<?> getAll() {
        return productService.getAll();
    }

    @GetMapping("/find-product/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/active-products")
    public ResponseEntity<?> getActiveProducts() {
        return productService.findByActive();
    }

    @GetMapping("/list-products/{category}")
    public ResponseEntity<?> findByCategory(@PathVariable Long category) {
        return productService.findByCategory(category);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ProductDto productDto) {
        return productService.create(productDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid ProductDto productDto) {
        return productService.update(productDto);
    }
}
