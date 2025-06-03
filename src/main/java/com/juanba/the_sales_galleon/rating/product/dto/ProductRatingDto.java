package com.juanba.the_sales_galleon.rating.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRatingDto {
    private Long id;
    private Long customer;
    private Long product;
    private Integer punctuation;
    private String comment;
    private LocalDate valuationDate;
}
