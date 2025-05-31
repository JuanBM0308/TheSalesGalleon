package com.juanba.the_sales_galleon.product.repository;

import com.juanba.the_sales_galleon.category.entity.Category;
import com.juanba.the_sales_galleon.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}
