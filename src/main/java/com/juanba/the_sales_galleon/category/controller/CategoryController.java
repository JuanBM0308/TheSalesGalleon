package com.juanba.the_sales_galleon.category.controller;

import com.juanba.the_sales_galleon.category.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/api/list-categories")
    public ResponseEntity<?> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/api/category/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }
}
