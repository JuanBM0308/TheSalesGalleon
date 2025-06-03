package com.juanba.the_sales_galleon.rating.vendor.repository;

import com.juanba.the_sales_galleon.user.entity.User;
import com.juanba.the_sales_galleon.rating.vendor.entity.VendorRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRatingRepository extends JpaRepository<VendorRating, Long> {
    List<VendorRating> findByVendor(User vendor);
}
