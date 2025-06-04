package com.juanba.the_sales_galleon.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juanba.the_sales_galleon.category.entity.Category;
import com.juanba.the_sales_galleon.purchase.details.entity.PurchaseDetails;
import com.juanba.the_sales_galleon.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pro")
    private Long id;

    @NotBlank
    @Column(name = "name_pro")
    private String name;

    @Column(name = "description_pro", columnDefinition = "TEXT")
    private String description;

    // * Realacion de Product a Category
    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_pro")
    private Category category;

    @NotNull
    @DecimalMin(value = "0.01")
    @Column(name = "price_pro")
    private BigDecimal price;

    @Column(name = "stock_pro")
    private Long stock;

    @Column(name = "image_pro", columnDefinition = "TEXT")
    private String image;

    @Column(name = "brand_pro")
    private String brand;

    @Column(name = "color_pro")
    private String color;

    @Column(name = "isNew_pro")
    private Boolean isNew;

    @NotNull
    @Column(name = "isActive_pro")
    private Boolean isActive;

    // * Relacion de Product a User
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_vendor_pro")
    private User vendor;

    // * Relacion de Product a PurchaseDetails
    @JsonIgnore
    @OneToMany(targetEntity = PurchaseDetails.class, fetch = FetchType.LAZY, mappedBy = "product")
    private List<PurchaseDetails> purchaseDetails;
}
