package com.juanba.the_sales_galleon.product.repository;

import com.juanba.the_sales_galleon.product.dto.ProductDto;
import com.juanba.the_sales_galleon.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
