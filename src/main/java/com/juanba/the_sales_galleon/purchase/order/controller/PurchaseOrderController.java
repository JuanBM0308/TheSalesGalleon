package com.juanba.the_sales_galleon.purchase.order.controller;

import com.juanba.the_sales_galleon.purchase.order.dto.PurchaseOrderDto;
import com.juanba.the_sales_galleon.purchase.order.entity.PurchaseOrder;
import com.juanba.the_sales_galleon.purchase.order.service.PurchaseOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-orders/api")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderServiceImpl purchaseOrderService;

    @GetMapping("/find-purchase-order/{id}")
    public ResponseEntity<PurchaseOrderDto> findById(@PathVariable Long id) {
        return purchaseOrderService.findById(id);
    }

    @GetMapping("/list-purchase-orders")
    public ResponseEntity<List<PurchaseOrderDto>> findAll() {
        return purchaseOrderService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<PurchaseOrder> create(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        return purchaseOrderService.create(purchaseOrderDto);
    }

    @PutMapping("/update")
    public ResponseEntity<PurchaseOrderDto> update(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        return purchaseOrderService.update(purchaseOrderDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return purchaseOrderService.delete(id);
    }
}