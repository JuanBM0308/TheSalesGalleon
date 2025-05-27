package com.juanba.the_sales_galleon.user.controller;

import com.juanba.the_sales_galleon.user.dto.UserDto;
import com.juanba.the_sales_galleon.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/api/list-users")
    public ResponseEntity<?> getAll() {
        return userService.getAll();
    }

    @GetMapping("/api/find-user-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/api/find-user-by-email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PutMapping("/api/deactivate-user/{id}")
    public ResponseEntity<?> desactivateUser(@PathVariable Long id) {
        return userService.deactivateUser(id);
    }

    @PutMapping("/api/activate-user/{id}")
    public ResponseEntity<?> activateUser(@PathVariable Long id) {
        return userService.activateUser(id);
    }

    @PostMapping("/api/create")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PutMapping("/api/update")
    public ResponseEntity<?> update(@RequestBody UserDto userDto) {
        return userService.update(userDto);
    }

    // ? Endpoint para que el usuario actualice la contraseña
    @PostMapping("/api/generate-password-recovery-code")
    public ResponseEntity<String> generatePasswordRecoveryCode(@RequestBody UserDto userDto) {
        return userService.forgotPassword(userDto);
    }

    @PutMapping("/api/change-password")
    public ResponseEntity<String> changePassword(@RequestBody UserDto userDto) {
        return userService.updatePassword(userDto);
    }
}
