package com.juanba.the_sales_galleon.category.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juanba.the_sales_galleon.category.util.ProductCategory;
import com.juanba.the_sales_galleon.product.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    // * Relacion de Categorias a Productos
    @JsonIgnore
    @OneToMany(targetEntity = Product.class, fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product> products;
}
