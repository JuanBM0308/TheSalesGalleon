package com.juanba.the_sales_galleon.rating.product.entity;

import com.juanba.the_sales_galleon.product.entity.Product;
import com.juanba.the_sales_galleon.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_product_rating")
public class ProductRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pr")
    private Long id;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_customer_pr")
    private User customer;

    @NotNull
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "id_product_pr")
    private Product product;

    @NotNull
    @Max(5)
    @Min(1)
    @Column(name = "punctuation_pr")
    private Integer punctuation;

    @Column(name = "comment_pr")
    private String comment;

    @NotNull
    @Column(name = "valuation_date_pr")
    private LocalDate valuationDate;
}
