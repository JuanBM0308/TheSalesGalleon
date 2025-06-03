package com.juanba.the_sales_galleon.rating.product.repository;

import com.juanba.the_sales_galleon.product.entity.Product;
import com.juanba.the_sales_galleon.rating.product.entity.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {
    List<ProductRating> findByProduct(Product product);
}
