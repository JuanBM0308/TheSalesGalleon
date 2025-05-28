package com.juanba.the_sales_galleon.product.entity;

import com.juanba.the_sales_galleon.category.util.ProductCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "category_pro")
    private ProductCategory category;

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
}
