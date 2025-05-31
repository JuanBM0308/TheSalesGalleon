package com.juanba.the_sales_galleon.category.repository;

import com.juanba.the_sales_galleon.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
