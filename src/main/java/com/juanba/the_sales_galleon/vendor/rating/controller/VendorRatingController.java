package com.juanba.the_sales_galleon.vendor.rating.controller;

import com.juanba.the_sales_galleon.vendor.rating.dto.VendorRatingDto;
import com.juanba.the_sales_galleon.vendor.rating.service.VendorRatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendor-ratings/api")
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
}
