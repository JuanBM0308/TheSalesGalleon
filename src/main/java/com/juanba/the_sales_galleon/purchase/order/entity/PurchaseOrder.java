package com.juanba.the_sales_galleon.purchase.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juanba.the_sales_galleon.purchase.details.entity.PurchaseDetails;
import com.juanba.the_sales_galleon.purchase.order.util.ShipmentStatus;
import com.juanba.the_sales_galleon.purchase.order.util.StatusPurchaseOrder;
import com.juanba.the_sales_galleon.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_purchase_order")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_po")
    private Long id;

    @Column(name = "creation_date_po")
    private LocalDate creationDate;

    // * Relacion de PurchaseOrder a User
    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_customer_po")
    private User customer;

    @Column(name = "shipping_address_po")
    private String shippingAddress;

    @Column(name = "total_po")
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status_po")
    private StatusPurchaseOrder paymentStatus;

    @Column(name = "payment_method_po")
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "shipment_status_po")
    private ShipmentStatus shipmentStatus;

    // * Relacion de PurchaseOrder a PurchaseDetails
    @JsonIgnore
    @OneToMany(targetEntity = PurchaseDetails.class, fetch = FetchType.LAZY, mappedBy = "purchaseOrder")
    private List<PurchaseDetails> purchaseDetailsList;
}
