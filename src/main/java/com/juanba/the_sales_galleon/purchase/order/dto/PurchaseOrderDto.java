package com.juanba.the_sales_galleon.purchase.order.dto;

import com.juanba.the_sales_galleon.purchase.order.util.ShipmentStatus;
import com.juanba.the_sales_galleon.purchase.order.util.StatusPurchaseOrder;
import com.juanba.the_sales_galleon.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseOrderDto {
    private Long id;
    private LocalDate creationDate;
    private Long customer;
    private String shippingAddress;
    private BigDecimal total;
    private String paymentStatus;
    private String paymentMethod;
    private String shipmentStatus;
}
