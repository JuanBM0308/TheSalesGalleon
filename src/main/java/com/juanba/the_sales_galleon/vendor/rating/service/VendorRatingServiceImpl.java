package com.juanba.the_sales_galleon.vendor.rating.service;

import com.juanba.the_sales_galleon.user.entity.User;
import com.juanba.the_sales_galleon.user.repository.UserRepository;
import com.juanba.the_sales_galleon.vendor.rating.dto.VendorRatingDto;
import com.juanba.the_sales_galleon.vendor.rating.entity.VendorRating;
import com.juanba.the_sales_galleon.vendor.rating.repository.VendorRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorRatingServiceImpl {

    @Autowired
    private VendorRatingRepository vendorRatingRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> findByVendor(Long id) {
        try {
            Optional<User> vendorFind = userRepository.findById(id);
            if (vendorFind.isEmpty()) {
                return ResponseEntity.status(404).body("Vendor not found 🙍‍♂️.");
            }

            List<VendorRating> vendorRatings = vendorRatingRepository.findByVendor(vendorFind.get());
            List<VendorRatingDto> vendorRatingDtos = vendorRatings.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.status(200).body(vendorRatingDtos);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ups! Internal server error 😣.");
        }
    }

    public ResponseEntity<?> create(VendorRatingDto vendorRatingDto) {
        try {
            Optional<User> vendor = userRepository.findById(vendorRatingDto.getVendor());
            if (vendor.isEmpty()) {
                return ResponseEntity.status(404).body("Vendor not found 👤.");
            }

            Optional<User> customer = userRepository.findById(vendorRatingDto.getCustomer());
            if (customer.isEmpty()) {
                return ResponseEntity.status(404).body("Customer not found 👤.");
            }

            VendorRating vendorRating = VendorRating.builder()
                    .id(vendorRatingDto.getId())
                    .vendor(vendor.get())
                    .rating(vendorRatingDto.getRating())
                    .comment(vendorRatingDto.getComment())
                    .valuationDate(vendorRatingDto.getValuationDate())
                    .customer(customer.get())
                    .build();

            vendorRatingRepository.save(vendorRating);

            return ResponseEntity.status(201).body(convertToDto(vendorRating));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ups! Internal server error 😣.");
        }
    }

    public VendorRatingDto convertToDto(VendorRating vendorRating) {
        VendorRatingDto.VendorRatingDtoBuilder builder = VendorRatingDto.builder()
                .id(vendorRating.getId())
                .vendor(vendorRating.getVendor().getId())
                .rating(vendorRating.getRating())
                .comment(vendorRating.getComment())
                .valuationDate(vendorRating.getValuationDate())
                .customer(vendorRating.getCustomer().getId());

        return builder.build();
    }
}
