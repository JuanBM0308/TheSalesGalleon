package com.juanba.the_sales_galleon.rating.product.controller;

import com.juanba.the_sales_galleon.rating.product.dto.ProductRatingDto;
import com.juanba.the_sales_galleon.rating.product.service.ProductRatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products-ratings/api")
public class ProductRatingController {

    @Autowired
    private ProductRatingServiceImpl productRatingService;

    @GetMapping("/find-product/{id}")
    public ResponseEntity<?> findByProduct(@PathVariable Long id) {
        return productRatingService.findByProduct(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductRatingDto productRatingDto) {
        return productRatingService.create(productRatingDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(Long id) {
        return productRatingService.delete(id);
    }
}
