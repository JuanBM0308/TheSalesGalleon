package com.juanba.the_sales_galleon.category.entity;

import com.juanba.the_sales_galleon.category.util.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_product_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ca")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name_ca")
    private ProductCategory name;

    @Column(name = "description_ca", columnDefinition = "TEXT")
    private String description;

}
