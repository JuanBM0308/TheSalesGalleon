package com.juanba.the_sales_galleon.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.juanba.the_sales_galleon.authentication.util.Role;
import com.juanba.the_sales_galleon.product.entity.Product;
import com.juanba.the_sales_galleon.rating.vendor.entity.VendorRating;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_us")
    private Long id;

    @NotNull
    @Column(name = "name_us")
    private String name;

    @NotNull
    @Column(name = "username_us", unique = true)
    private String username;

    @NotNull
    @Column(name = "password_us", columnDefinition = "TEXT")
    private String password;

    @NotNull
    @Column(name = "email_us", columnDefinition = "VARCHAR(500)", unique = true)
    private String email;

    @NotNull
    @Column(name = "phone_number_us")
    private String phoneNumber;

    @NotNull
    @Column(name = "address_us",  columnDefinition = "VARCHAR(500)")
    private String address;

    @Column(name = "total_purchases_us", columnDefinition = "INTEGER DEFAULT 0")
    private Integer total_purchases;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_us")
    private Role role;

    @Column(name = "change_password_code_us")
    private String changePasswordCode;

    @Column(name = "change_password_code_expiration_us")
    private LocalDateTime changePasswordCodeExpiration;

    @NotNull
    @Column(name = "is_active_us")
    private Boolean isActive;

    // * Relacion de User a Product
    @JsonIgnore
    @OneToMany(targetEntity = Product.class, fetch = FetchType.LAZY, mappedBy = "vendor")
    private List<Product> products;

    // *Relacion de User a VendorRating (customer)
    @JsonIgnore
    @OneToMany(targetEntity = VendorRating.class, fetch = FetchType.LAZY, mappedBy = "customer")
    private List<VendorRating> ratingsCustomer;

    // *Relacion de User a VendorRating (vendor)
    @JsonIgnore
    @OneToMany(targetEntity = VendorRating.class, fetch = FetchType.LAZY, mappedBy = "vendor")
    private List<VendorRating> ratingsVendor;

    // * Make our authorities understand spring
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = role.getPermissions().stream()
                .map(permissionEnum -> new SimpleGrantedAuthority(permissionEnum.name()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

        return authorities;
    }

    // * Set user properties of UserDetails
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
