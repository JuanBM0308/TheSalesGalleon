package com.juanba.the_sales_galleon.purchase.details.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDetailsDto {
    private Long id;
    private Long purchaseOrder;
    private Long product;
    private Long quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}
