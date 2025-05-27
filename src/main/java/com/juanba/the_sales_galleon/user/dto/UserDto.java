package com.juanba.the_sales_galleon.user.dto;

import com.juanba.the_sales_galleon.authentication.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private Role role;
    private Integer total_purchases;
    private Boolean isActive;

    // ? Codigo para cambiar contraseña
    private String changePasswordCode;
    private LocalDateTime changePasswordCodeExpiration;
}
