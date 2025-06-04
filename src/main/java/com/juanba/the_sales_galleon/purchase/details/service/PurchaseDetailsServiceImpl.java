package com.juanba.the_sales_galleon.purchase.details.service;

import com.juanba.the_sales_galleon.product.entity.Product;
import com.juanba.the_sales_galleon.product.repository.ProductRepository;
import com.juanba.the_sales_galleon.purchase.details.dto.PurchaseDetailsDto;
import com.juanba.the_sales_galleon.purchase.details.entity.PurchaseDetails;
import com.juanba.the_sales_galleon.purchase.details.repository.PurchaseDetailsRepository;
import com.juanba.the_sales_galleon.purchase.order.entity.PurchaseOrder;
import com.juanba.the_sales_galleon.purchase.order.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseDetailsServiceImpl {

    @Autowired
    private PurchaseDetailsRepository purchaseDetailsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public ResponseEntity<PurchaseDetailsDto> findById(Long id) {
        try {
            Optional<PurchaseDetails> purchaseDetails = purchaseDetailsRepository.findById(id);
            return purchaseDetails.map(details -> ResponseEntity.ok().body(convertToDto(details)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<List<PurchaseDetailsDto>> findByOrder(Long id) {
        try {
            Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(id);
            if (purchaseOrder.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            List<PurchaseDetails> purchaseDetails = purchaseDetailsRepository.findByPurchaseOrder(purchaseOrder.get());
            List<PurchaseDetailsDto> purchaseDetailsDtos = purchaseDetails.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(purchaseDetailsDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<PurchaseDetails> create(PurchaseDetailsDto purchaseDetailsDto) {
        try {
            Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(purchaseDetailsDto.getId());
            if (purchaseOrder.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Optional<Product> product = productRepository.findById(purchaseDetailsDto.getProduct());
            if (product.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            PurchaseDetails purchaseDetails =PurchaseDetails.builder()
                    .id(purchaseDetailsDto.getId())
                    .purchaseOrder(purchaseOrder.get())
                    .product(product.get())
                    .quantity(purchaseDetailsDto.getQuantity())
                    .unitPrice(purchaseDetailsDto.getUnitPrice())
                    .subtotal(purchaseDetailsDto.getSubtotal())
                    .build();

            purchaseDetailsRepository.save(purchaseDetails);

            return ResponseEntity.status(201).body(purchaseDetails);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<String> delete(Long id) {
        try {
            Optional<PurchaseDetails> purchaseDetails = purchaseDetailsRepository.findById(id);
            if (purchaseDetails.isEmpty()) {
                return ResponseEntity.status(404).body("Purchase details not found.");
            }

            purchaseDetailsRepository.deleteById(id);

            return ResponseEntity.status(204).body("Purchase details successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<PurchaseDetailsDto> update(PurchaseDetailsDto purchaseDetailsDto) {
        try {
            Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepository.findById(purchaseDetailsDto.getPurchaseOrder());
            if (purchaseOrder.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            Optional<Product> product = productRepository.findById(purchaseDetailsDto.getProduct());
            if (product.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            PurchaseDetails purchaseDetails = PurchaseDetails.builder()
                    .id(purchaseDetailsDto.getId())
                    .purchaseOrder(purchaseOrder.get())
                    .product(product.get())
                    .quantity(purchaseDetailsDto.getQuantity())
                    .unitPrice(purchaseDetailsDto.getUnitPrice())
                    .subtotal(purchaseDetailsDto.getSubtotal())
                    .build();

            purchaseDetailsRepository.save(purchaseDetails);

            return ResponseEntity.status(200).body(convertToDto(purchaseDetails));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    public PurchaseDetailsDto convertToDto(PurchaseDetails purchaseDetails) {
        PurchaseDetailsDto.PurchaseDetailsDtoBuilder builder = PurchaseDetailsDto.builder()
                .id(purchaseDetails.getId())
                .purchaseOrder(purchaseDetails.getPurchaseOrder().getId())
                .product(purchaseDetails.getProduct().getId())
                .quantity(purchaseDetails.getQuantity())
                .unitPrice(purchaseDetails.getUnitPrice())
                .subtotal(purchaseDetails.getSubtotal());

        return builder.build();
    }
}
