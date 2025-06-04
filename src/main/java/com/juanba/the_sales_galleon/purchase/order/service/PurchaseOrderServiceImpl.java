package com.juanba.the_sales_galleon.purchase.order.service;

import com.juanba.the_sales_galleon.purchase.details.repository.PurchaseDetailsRepository;
import com.juanba.the_sales_galleon.purchase.order.dto.PurchaseOrderDto;
import com.juanba.the_sales_galleon.purchase.order.entity.PurchaseOrder;
import com.juanba.the_sales_galleon.purchase.order.repository.PurchaseOrderRepository;
import com.juanba.the_sales_galleon.purchase.order.util.ShipmentStatus;
import com.juanba.the_sales_galleon.purchase.order.util.StatusPurchaseOrder;
import com.juanba.the_sales_galleon.user.entity.User;
import com.juanba.the_sales_galleon.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseDetailsRepository purchaseDetailsRepository;

    public ResponseEntity<PurchaseOrderDto> findById(Long id) {
        try {
            Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
            return purchaseOrder.map(order -> ResponseEntity.ok().body(convertToDto(order)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<PurchaseOrderDto>> findAll() {
        try {
            List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
            List<PurchaseOrderDto> purchaseOrderDtos = purchaseOrders.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(purchaseOrderDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<PurchaseOrder> create(PurchaseOrderDto purchaseOrderDto) {
        try {
            Optional<User> customer = userRepository.findById(purchaseOrderDto.getCustomer());
            if (customer.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                    .creationDate(LocalDate.now())
                    .customer(customer.get())
                    .shippingAddress(purchaseOrderDto.getShippingAddress())
                    .total(purchaseOrderDto.getTotal())
                    .paymentStatus(StatusPurchaseOrder.valueOf(purchaseOrderDto.getPaymentStatus()))
                    .paymentMethod(purchaseOrderDto.getPaymentMethod())
                    .shipmentStatus(ShipmentStatus.valueOf(purchaseOrderDto.getShipmentStatus()))
                    .build();

            purchaseOrderRepository.save(purchaseOrder);
            return ResponseEntity.status(201).body(purchaseOrder);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<String> delete(Long id) {
        try {
            Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
            if (purchaseOrder.isEmpty()) {
                return ResponseEntity.status(404).body("Purchase order not found.");
            }

            purchaseOrderRepository.deleteById(id);

            return ResponseEntity.status(204).body("Purchase order successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<PurchaseOrderDto> update(PurchaseOrderDto purchaseOrderDto) {
        try {
            Optional<PurchaseOrder> existingOrder = purchaseOrderRepository.findById(purchaseOrderDto.getId());
            if (existingOrder.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Optional<User> customer = userRepository.findById(purchaseOrderDto.getCustomer());
            if (customer.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            PurchaseOrder updatedOrder = existingOrder.get();
            updatedOrder.setCustomer(customer.get());
            updatedOrder.setShippingAddress(purchaseOrderDto.getShippingAddress());
            updatedOrder.setTotal(purchaseOrderDto.getTotal());
            updatedOrder.setPaymentStatus(StatusPurchaseOrder.valueOf(purchaseOrderDto.getPaymentStatus()));
            updatedOrder.setPaymentMethod(purchaseOrderDto.getPaymentMethod());
            updatedOrder.setShipmentStatus(ShipmentStatus.valueOf(purchaseOrderDto.getShipmentStatus()));

            purchaseOrderRepository.save(updatedOrder);

            return ResponseEntity.status(200).body(convertToDto(updatedOrder));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public PurchaseOrderDto convertToDto(PurchaseOrder purchaseOrder) {
        return PurchaseOrderDto.builder()
                .id(purchaseOrder.getId())
                .creationDate(purchaseOrder.getCreationDate())
                .customer(purchaseOrder.getCustomer() != null ? purchaseOrder.getCustomer().getId() : null)
                .shippingAddress(purchaseOrder.getShippingAddress())
                .total(purchaseOrder.getTotal())
                .paymentStatus(purchaseOrder.getPaymentStatus() != null ? purchaseOrder.getPaymentStatus().name() : null)
                .paymentMethod(purchaseOrder.getPaymentMethod())
                .shipmentStatus(purchaseOrder.getShipmentStatus() != null ? purchaseOrder.getShipmentStatus().name() : null)
                .build();
    }
}