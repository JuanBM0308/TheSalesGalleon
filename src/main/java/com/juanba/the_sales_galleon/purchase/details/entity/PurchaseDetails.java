package com.juanba.the_sales_galleon.purchase.details.entity;

import com.juanba.the_sales_galleon.product.entity.Product;
import com.juanba.the_sales_galleon.purchase.order.entity.PurchaseOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_purchase_details")
public class PurchaseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pd")
    private Long id;

    // * Relacion de PurchaseDetails a PurchaseOrder
    @ManyToOne(targetEntity = PurchaseOrder.class)
    @JoinColumn(name = "id_purchase_order_pd")
    private PurchaseOrder purchaseOrder;

    // * Relacion de PurchaseDetails a Product
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "id_product_pd")
    private Product product;

    @Column(name = "quantity_pd")
    private Long quantity;

    @NotNull
    @DecimalMin(value = "0.01")
    @Column(name = "unit_price_pd")
    private BigDecimal unitPrice;

    @NotNull
    @DecimalMin(value = "0.01")
    @Column(name = "subtotal_pd")
    private BigDecimal subtotal;
}
