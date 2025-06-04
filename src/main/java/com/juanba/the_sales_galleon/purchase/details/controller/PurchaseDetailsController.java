package com.juanba.the_sales_galleon.purchase.details.controller;

import com.juanba.the_sales_galleon.purchase.details.dto.PurchaseDetailsDto;
import com.juanba.the_sales_galleon.purchase.details.entity.PurchaseDetails;
import com.juanba.the_sales_galleon.purchase.details.service.PurchaseDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase-details/api")
public class PurchaseDetailsController {

    @Autowired
    private PurchaseDetailsServiceImpl purchaseDetailsService;

    @GetMapping("/find-purchase-details/{id}")
    public ResponseEntity<PurchaseDetailsDto> findById(@PathVariable Long id) {
        return purchaseDetailsService.findById(id);
    }

    @GetMapping("/find-by-order/{orderId}")
    public ResponseEntity<List<PurchaseDetailsDto>> findByOrder(@PathVariable Long orderId) {
        return purchaseDetailsService.findByOrder(orderId);
    }

    @PostMapping("/create")
    public ResponseEntity<PurchaseDetails> create(@RequestBody PurchaseDetailsDto purchaseDetailsDto) {
        return purchaseDetailsService.create(purchaseDetailsDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return purchaseDetailsService.delete(id);
    }

    @PutMapping("/update")
    public ResponseEntity<PurchaseDetailsDto> update(@RequestBody PurchaseDetailsDto purchaseDetailsDto) {
        return purchaseDetailsService.update(purchaseDetailsDto);
    }
}