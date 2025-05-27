package com.juanba.the_sales_galleon.authentication.service;

import com.juanba.the_sales_galleon.authentication.dto.AuthenticationRequest;
import com.juanba.the_sales_galleon.authentication.dto.AuthenticationResponse;
import com.juanba.the_sales_galleon.user.entity.User;
import com.juanba.the_sales_galleon.user.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse login(@Valid AuthenticationRequest authRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            authRequest.getUsername(), authRequest.getPassword()
        );

        authenticationManager.authenticate(authToken);

        User user = userRepository.findByUsername(authRequest.getUsername()).get();

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponse(jwt);
    }

    public Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole());
        extraClaims.put("permissions", user.getAuthorities());

        return extraClaims;
    }
}
