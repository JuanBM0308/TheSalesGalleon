package com.juanba.the_sales_galleon.product.repository;

import com.juanba.the_sales_galleon.category.entity.Category;
import com.juanba.the_sales_galleon.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}
