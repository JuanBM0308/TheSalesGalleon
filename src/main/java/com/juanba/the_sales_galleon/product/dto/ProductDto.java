package com.juanba.the_sales_galleon.product.dto;

import com.juanba.the_sales_galleon.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Long category;
    private BigDecimal price;
    private Long stock;
    private String image;
    private String brand;
    private String color;
    private Boolean isNew;
    private Boolean isActive;
    private Long vendor;
}
