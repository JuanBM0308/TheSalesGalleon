package com.juanba.the_sales_galleon.rating.vendor.controller;

import com.juanba.the_sales_galleon.rating.vendor.dto.VendorRatingDto;
import com.juanba.the_sales_galleon.rating.vendor.service.VendorRatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendors-ratings/api")
public class VendorRatingController {

    @Autowired
    private VendorRatingServiceImpl vendorRatingService;

    @GetMapping("/find-vendor/{id}")
    public ResponseEntity<?> findByVendor(@PathVariable Long id) {
        return vendorRatingService.findByVendor(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody VendorRatingDto vendorRatingDto) {
        return vendorRatingService.create(vendorRatingDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return vendorRatingService.delete(id);
    }
}
