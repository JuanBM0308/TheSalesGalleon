package com.juanba.the_sales_galleon.authentication.controller;

import com.juanba.the_sales_galleon.authentication.dto.AuthenticationRequest;
import com.juanba.the_sales_galleon.authentication.dto.AuthenticationResponse;
import com.juanba.the_sales_galleon.authentication.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/greet-developer")
    public String helloDeveloper() {
        return "Hello developer, I hope you enjoy this API ✨.";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authRequest) {
        AuthenticationResponse jwtDto = authenticationService.login(authRequest);
        return ResponseEntity.ok(jwtDto);
    }
}
