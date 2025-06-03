package com.juanba.the_sales_galleon.rating.vendor.entity;

import com.juanba.the_sales_galleon.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_vendor_rating")
public class VendorRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vr")
    private Long id;

    // * Relacion entre VendorRating y User (vendor)
    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_vendor_vr")
    private User vendor;

    @NotNull
    @Column(name = "rating_vr")
    private Double rating; // ? Puntuacion

    @Column(name = "comment_vr")
    private String comment;

    @NotNull
    @Column(name = "valuation_date_vr")
    private LocalDate valuationDate;

    // * Relacion entre VendorRating y User (customer)
    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_customer_vr")
    private User customer;
}
