package com.juanba.the_sales_galleon.vendor.rating.dto;

import com.juanba.the_sales_galleon.user.entity.User;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorRatingDto {
    private Long id;
    private Long vendor;
    private Double rating;
    private String comment;
    private LocalDate valuationDate;
    private Long customer;
}
