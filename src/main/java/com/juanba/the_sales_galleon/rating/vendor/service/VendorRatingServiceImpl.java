package com.juanba.the_sales_galleon.rating.vendor.service;

import com.juanba.the_sales_galleon.user.entity.User;
import com.juanba.the_sales_galleon.user.repository.UserRepository;
import com.juanba.the_sales_galleon.rating.vendor.dto.VendorRatingDto;
import com.juanba.the_sales_galleon.rating.vendor.entity.VendorRating;
import com.juanba.the_sales_galleon.rating.vendor.repository.VendorRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            if (vendor.isEmpty() || !vendor.get().getRole().name().equals("VENDOR")) {
                return ResponseEntity.status(404).body("Vendor not found 👤.");
            }

            Optional<User> customer = userRepository.findById(vendorRatingDto.getCustomer());
            if (customer.isEmpty()) {
                return ResponseEntity.status(404).body("Customer not found 👤.");
            }

            if (vendorRatingDto.getRating() > 0 && vendorRatingDto.getRating() <= 5.0) {
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
            }

            return ResponseEntity.status(406).body("Minimum rating 1.0 and maximum rating 5.0 😣.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ups! Internal server error 😣.");
        }
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            if (vendorRatingRepository.existsById(id)) {
                return ResponseEntity.status(404).body("Rating not found 👤.");
            }

            vendorRatingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
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
