package com.juanba.the_sales_galleon.category.service;

import com.juanba.the_sales_galleon.category.entity.Category;
import com.juanba.the_sales_galleon.category.repository.CategoryRepository;
import com.juanba.the_sales_galleon.response.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl {

    @Autowired
    CategoryRepository categoryRepository;

    public ResponseEntity<?> getAll() {
        try {
            List<Category> categories = categoryRepository.findAll();

            if (categories.isEmpty())
                return new ResponseEntity<>(new ResponseDto(false, "No categories found.", null), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(new ResponseDto(true, "Categories found 😊.", categories), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Ups! An unexpected error occurred while retrieving categories.", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findById(Long id) {
        try {
            Optional<Category> category = categoryRepository.findById(id);

            if (category.isEmpty())
                return new ResponseEntity<>(new ResponseDto(false, "Category not found.", null), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(new ResponseDto(true, "Category found 😊.", category.get()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error searching for category.", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
