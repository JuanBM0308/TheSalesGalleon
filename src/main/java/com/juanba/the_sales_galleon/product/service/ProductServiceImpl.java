package com.juanba.the_sales_galleon.product.service;

import com.juanba.the_sales_galleon.category.entity.Category;
import com.juanba.the_sales_galleon.category.repository.CategoryRepository;
import com.juanba.the_sales_galleon.product.dto.ProductDto;
import com.juanba.the_sales_galleon.response.ResponseDto;
import com.juanba.the_sales_galleon.product.entity.Product;
import com.juanba.the_sales_galleon.product.repository.ProductRepository;
import com.juanba.the_sales_galleon.user.entity.User;
import com.juanba.the_sales_galleon.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> getAll() {
        try {
            List<Product> products = productRepository.findAll();
            List<ProductDto> productDtos = products.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseDto(true, "Products successfully recovered.", productDtos), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "No products found 😥", null), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> findById(Long id) {
        try {
            Optional<Product> productOptional = productRepository.findById(id);

            if (productOptional.isEmpty())
                return new ResponseEntity<>(new ResponseDto(false, "No product found 😥", null), HttpStatus.NOT_FOUND);

            ProductDto productDto = convertToDto(productOptional.get());

            return new ResponseEntity<>(new ResponseDto(true, "Product successfully recovered.", productDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Internal server error 😖", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> findByActive() {
        try {
            List<Product> products = productRepository.findAll().stream()
                    .filter(Product::getIsActive)
                    .collect(Collectors.toList());
            List<ProductDto> productDtos = products.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            if (productDtos.isEmpty())
                return new ResponseEntity<>(new ResponseDto(false, "No active products found 😥", null), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(new ResponseDto(true, "Active products 🛒", productDtos), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Internal server error 😖", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findByCategory(Long id) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(id);
            if (categoryOptional.isEmpty()) {
                return new ResponseEntity<>(new ResponseDto(false, "Non-existent product category ❌", null), HttpStatus.NOT_FOUND);
            }

            List<Product> products = productRepository.findByCategory(categoryOptional.get());
            List<ProductDto> productDtos = products.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(new ResponseDto(true, "Products successfully recovered.", productDtos), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Internal server error 😖", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> create(ProductDto productDto) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategory());
            if (categoryOptional.isEmpty()) {
                return new ResponseEntity<>(new ResponseDto(false, "Non-existent product category ❌", null), HttpStatus.NOT_FOUND);
            }

            Optional<User> vendorOptional = userRepository.findById(productDto.getVendor());
            if (vendorOptional.isEmpty()) {
                return new ResponseEntity<>(new ResponseDto(false, "User not found ❌", null), HttpStatus.NOT_FOUND);
            }

            Product product = Product.builder()
                    .name(productDto.getName())
                    .description(productDto.getDescription())
                    .category(categoryOptional.get())
                    .price(productDto.getPrice())
                    .stock(productDto.getStock())
                    .image(productDto.getImage())
                    .brand(productDto.getBrand())
                    .color(productDto.getColor())
                    .isNew(productDto.getIsNew())
                    .isActive(productDto.getIsActive())
                    .vendor(vendorOptional.get())
                    .build();

            Product productSaved = productRepository.save(product);

            return new ResponseEntity<>(new ResponseDto(true, "Product successfully created.", convertToDto(productSaved)), HttpStatus.CREATED);
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
            if (!productRepository.existsById(productDto.getId()))
                return new ResponseEntity<>(new ResponseDto(false, "Product not found 😥", null), HttpStatus.NOT_FOUND);

            Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategory());
            if (categoryOptional.isEmpty()) {
                return new ResponseEntity<>(new ResponseDto(false, "Non-existent product category ❌", null), HttpStatus.NOT_FOUND);
            }

            Optional<User> vendorOptional = userRepository.findById(productDto.getVendor());
            if (vendorOptional.isEmpty()) {
                return new ResponseEntity<>(new ResponseDto(false, "User not found ❌", null), HttpStatus.NOT_FOUND);
            }

            Product product = Product.builder()
                    .id(productDto.getId())
                    .name(productDto.getName())
                    .description(productDto.getDescription())
                    .category(categoryOptional.get())
                    .price(productDto.getPrice())
                    .stock(productDto.getStock())
                    .image(productDto.getImage())
                    .brand(productDto.getBrand())
                    .color(productDto.getColor())
                    .isNew(productDto.getIsNew())
                    .isActive(productDto.getIsActive())
                    .vendor(vendorOptional.get())
                    .build();

            Product productUpdate = productRepository.save(product);

            return new ResponseEntity<>(new ResponseDto(true, "Product updated successfully.", convertToDto(productUpdate)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "The product could not be deleted ❌", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ProductDto convertToDto(Product product) {
        ProductDto.ProductDtoBuilder builder = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .image(product.getImage())
                .brand(product.getBrand())
                .color(product.getColor())
                .isNew(product.getIsNew())
                .isActive(product.getIsActive());

        if (product.getCategory() != null) {
            builder.category(product.getCategory().getId());
        }
        if (product.getVendor() != null) {
            builder.vendor(product.getVendor().getId());
        }

        return builder.build();
    }
}