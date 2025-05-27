package com.juanba.the_sales_galleon.product.service;

import com.juanba.the_sales_galleon.product.dto.ProductDto;
import com.juanba.the_sales_galleon.response.ResponseDto;
import com.juanba.the_sales_galleon.product.entity.Product;
import com.juanba.the_sales_galleon.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> getAll() {
        try {
            List<Product> products = productRepository.findAll();
            return new ResponseEntity<>(new ResponseDto(true, "Products successfully recovered.", products), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "No products found 😥", null), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> findById(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isEmpty())
                return new ResponseEntity<>(new ResponseDto(false, "No product found 😥", null), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(new ResponseDto(true, "Product successfully recovered.", product), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Internal server error 😖", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> findByActive() {
        try {
            List<Product> products = productRepository.findAll().stream()
                    .filter(Product::getIsActive)
                    .toList();

            if (products.isEmpty())
                return new ResponseEntity<>(new ResponseDto(false, "No products found 😥", null), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(new ResponseDto(false, "Active products 🛒", products), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Internal server error 😖", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> create(ProductDto productDto) {
        try {
            Product product = Product.builder()
                    .name(productDto.getName())
                    .description(productDto.getDescription())
                    .category(productDto.getCategory())
                    .price(productDto.getPrice())
                    .stock(productDto.getStock())
                    .image(productDto.getImage())
                    .brand(productDto.getBrand())
                    .color(productDto.getColor())
                    .isNew(productDto.getIsNew())
                    .isActive(productDto.getIsActive())
                    .build();

            Product productSaved = productRepository.save(product);

            return new ResponseEntity<>(new ResponseDto(true, "Product successfully created.", productSaved), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "The product could not be created ❌", null), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            if (!productRepository.existsById(id))
                return new ResponseEntity<>(new ResponseDto(false, "Product not found 😥", null), HttpStatus.NOT_FOUND);

            productRepository.deleteById(id);

            return new ResponseEntity<>(new ResponseDto(true, "Product successfully deleted.", null), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "The product could not be deleted ❌", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> update(ProductDto productDto) {
        try {
            Long id = productDto.getId();
            if (!productRepository.existsById(id))
                return new ResponseEntity<>(new ResponseDto(false, "Product not found 😥", null), HttpStatus.NOT_FOUND);

            Product product = Product.builder()
                    .id(id)
                    .name(productDto.getName())
                    .description(productDto.getDescription())
                    .category(productDto.getCategory())
                    .price(productDto.getPrice())
                    .stock(productDto.getStock())
                    .image(productDto.getImage())
                    .brand(productDto.getBrand())
                    .color(productDto.getColor())
                    .isNew(productDto.getIsNew())
                    .isActive(productDto.getIsActive())
                    .build();

            Product productUpdate = productRepository.save(product);

            return new ResponseEntity<>(new ResponseDto(true, "Product updated successfully.", productUpdate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "The product could not be deleted ❌", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
