package com.juanba.the_sales_galleon.rating.product.service;

import com.juanba.the_sales_galleon.product.entity.Product;
import com.juanba.the_sales_galleon.product.repository.ProductRepository;
import com.juanba.the_sales_galleon.rating.product.dto.ProductRatingDto;
import com.juanba.the_sales_galleon.rating.product.entity.ProductRating;
import com.juanba.the_sales_galleon.rating.product.repository.ProductRatingRepository;
import com.juanba.the_sales_galleon.rating.vendor.entity.VendorRating;
import com.juanba.the_sales_galleon.user.entity.User;
import com.juanba.the_sales_galleon.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductRatingServiceImpl {

    @Autowired
    private ProductRatingRepository productRatingRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> findByProduct(Long id) {
        try {
            Optional<Product> productFind = productRepository.findById(id);
            if (productFind.isEmpty()) {
                return ResponseEntity.status(404).body("Product not found 🙍‍♂️.");
            }

            List<ProductRating> productRatings = productRatingRepository.findByProduct(productFind.get());
            List<ProductRatingDto> productRatingDtos = productRatings.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.status(200).body(productRatingDtos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ups! Internal server error 🤯.");
        }
    }

    public ResponseEntity<?> create(ProductRatingDto productRatingDto) {
        try {
            Optional<Product> product = productRepository.findById(productRatingDto.getId());
            if (product.isEmpty()) {
                return ResponseEntity.status(404).body("Product not found 📦.");
            }

            Optional<User> customer = userRepository.findById(productRatingDto.getCustomer());
            if (customer.isEmpty()) {
                return ResponseEntity.status(404).body("Customer not found 👤.");
            }

            ProductRating productRating = ProductRating.builder()
                    .id(productRatingDto.getId())
                    .customer(customer.get())
                    .product(product.get())
                    .punctuation(productRatingDto.getPunctuation())
                    .comment(productRatingDto.getComment())
                    .valuationDate(productRatingDto.getValuationDate())
                    .build();

            productRatingRepository.save(productRating);
            return ResponseEntity.status(201).body(convertToDto(productRating));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ups! Internal server error 🤯.");
        }
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            if (!productRatingRepository.existsById(id)) {
                return ResponseEntity.status(404).body("Product not found 📦.");
            }

            productRatingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ups! Internal server error 🤯.");
        }
    }

    public ProductRatingDto convertToDto(ProductRating productRating) {
        ProductRatingDto.ProductRatingDtoBuilder builder = ProductRatingDto.builder()
                .id(productRating.getId())
                .customer(productRating.getCustomer().getId())
                .product(productRating.getProduct().getId())
                .punctuation(productRating.getPunctuation())
                .comment(productRating.getComment())
                .valuationDate(productRating.getValuationDate());

        return builder.build();
    }
}
