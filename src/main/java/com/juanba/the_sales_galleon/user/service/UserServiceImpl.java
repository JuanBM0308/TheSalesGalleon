package com.juanba.the_sales_galleon.user.service;

import com.juanba.the_sales_galleon.response.ResponseDto;
import com.juanba.the_sales_galleon.user.dto.UserDto;
import com.juanba.the_sales_galleon.user.entity.User;
import com.juanba.the_sales_galleon.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SendEmailServiceImpl sendEmailService;

    public ResponseEntity<?> getAll() {
        try {
            List<User> users = userRepository.findAll();
            return new ResponseEntity<>(new ResponseDto(true, "Users successfully recovered 👨👩.", users), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "No users found 😥", null), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> findById(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isEmpty())
                return new ResponseEntity<>(new ResponseDto(false, "User not found 👤", null), HttpStatus.NOT_FOUND);

            UserDto userDto = UserDto.builder()
                    .id(user.get().getId())
                    .name(user.get().getName())
                    .username(user.get().getUsername())
                    .phoneNumber(user.get().getPhoneNumber())
                    .address(user.get().getAddress())
                    .email(user.get().getEmail())
                    .role(user.get().getRole())
                    .total_purchases(user.get().getTotal_purchases())
                    .isActive(user.get().getIsActive())
                    .build();

            return new ResponseEntity<>(new ResponseDto(true, "User successfully recovered 📦.", userDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Internal server error 😖", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> findByEmail(String email) {
        try {
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isEmpty())
                return new ResponseEntity<>(new ResponseDto(false, "User not found 👤", null), HttpStatus.NOT_FOUND);

            UserDto userDto = UserDto.builder()
                    .id(user.get().getId())
                    .name(user.get().getName())
                    .username(user.get().getUsername())
                    .phoneNumber(user.get().getPhoneNumber())
                    .address(user.get().getAddress())
                    .email(user.get().getEmail())
                    .isActive(user.get().getIsActive())
                    .build();

            return new ResponseEntity<>(new ResponseDto(true, "User successfully recovered 📦.", userDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Internal server error 😖", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deactivateUser(Long id) {
        try {
            if (!userRepository.existsById(id))
                return new ResponseEntity<>(new ResponseDto(false, "User not found 👤", null), HttpStatus.NOT_FOUND);

            Optional<User> user = userRepository.findById(id);

            if (!user.get().getIsActive())
                return new ResponseEntity<>(new ResponseDto(false, "User was already disabled 🤔.", null), HttpStatus.NO_CONTENT);

            User disabledUser = user.get();
            disabledUser.setIsActive(false);

            return new ResponseEntity<>(new ResponseDto(true, "User successfully deactivated 👍.", disabledUser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Internal server error 😖", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> activateUser(Long id) {
        try {
            if (!userRepository.existsById(id))
                return new ResponseEntity<>(new ResponseDto(false, "User not found 👤", null), HttpStatus.NOT_FOUND);

            Optional<User> user = userRepository.findById(id);

            if (user.get().getIsActive())
                return new ResponseEntity<>(new ResponseDto(false, "User with was already activated 🤔.", null), HttpStatus.NO_CONTENT);

            User activatedUser = user.get();
            activatedUser.setIsActive(true);

            return new ResponseEntity<>(new ResponseDto(true, "User successfully deactivated 👍.", activatedUser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Internal server error 😖", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> create(UserDto userDto) {
        try {
            User user = User.builder()
                    .name(userDto.getName())
                    .username(userDto.getUsername())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .email(userDto.getEmail())
                    .phoneNumber(userDto.getPhoneNumber())
                    .address(userDto.getAddress())
                    .role(userDto.getRole())
                    .isActive(true)
                    .build();

            userRepository.save(user);

            return new ResponseEntity<>(new ResponseDto(true, "User successfully created 🙍‍♂️.", user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "The user could not be created ❌", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> delete(Long id) {
        try {
            if (!userRepository.existsById(id))
                return new ResponseEntity<>(new ResponseDto(false, "User not found 👤", null), HttpStatus.NOT_FOUND);

            userRepository.deleteById(id);

            return new ResponseEntity<>(new ResponseDto(true, "User successfully deleted.", null), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "The user could not be deleted ❌", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> update(UserDto userDto) {
        try {
            if (!userRepository.existsById(userDto.getId()))
                return new ResponseEntity<>(new ResponseDto(false, "User not found 👤", null), HttpStatus.NOT_FOUND);

            Optional<User> getActualUser = userRepository.findById(userDto.getId());

            User user = User.builder()
                    .name(userDto.getName())
                    .username(userDto.getUsername())
                    .password(passwordEncoder.encode(getActualUser.get().getPassword()))
                    .email(userDto.getEmail())
                    .phoneNumber(userDto.getPhoneNumber())
                    .address(userDto.getAddress())
                    .role(userDto.getRole())
                    .total_purchases(userDto.getTotal_purchases())
                    .isActive(userDto.getIsActive())
                    .build();

            User userUpdate  = userRepository.save(user);

            return new ResponseEntity<>(new ResponseDto(true, "User updated successfully.", userUpdate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "The user could not be updated ❌", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ? Logica para que el usuario actualice la contraseña
    public ResponseEntity<String> forgotPassword(UserDto userDto) {
        try {
            if (!userRepository.existsByEmail(userDto.getEmail())) {
                return ResponseEntity.status(404).body("User not found 👤");
            }

            User user = userRepository.findByEmail(userDto.getEmail()).get();
            String verificationCode = generateChangePasswordCode();

            user.setChangePasswordCode(verificationCode);
            user.setChangePasswordCodeExpiration(LocalDateTime.now().plusMinutes(20));
            userRepository.save(user);

            sendEmailService.sendPasswordResetEmail(userDto.getEmail(), verificationCode);

            return ResponseEntity.ok("Password reset code sent to your email address 📬.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Oops! There was an error processing your request: " + e);
        }
    }

    public ResponseEntity<String> updatePassword(UserDto userDto) {
        try {
            if (!userRepository.existsByEmail(userDto.getEmail()))
                return ResponseEntity.status(404).body("User not found 👤");

            User user = userRepository.findByEmail(userDto.getEmail()).get();

            if (user.getChangePasswordCode() == null || !user.getChangePasswordCode().equals(userDto.getChangePasswordCode()))
                return ResponseEntity.status(400).body("Incorrect password change code 🥶.");

            if (user.getChangePasswordCodeExpiration() == null || LocalDateTime.now().isAfter(user.getChangePasswordCodeExpiration())) {
                return ResponseEntity.status(400).body("Password change code has expired ⏰.");
            }

            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setChangePasswordCode(null);
            user.setChangePasswordCodeExpiration(null);
            userRepository.save(user);

            return ResponseEntity.status(200).body("Password updated successfully 🤩.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Oops! There was an error updating the password: " + e);
        }
    }

    public String generateChangePasswordCode() {
        StringBuilder generatedCode = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int acciiCode = ThreadLocalRandom.current().nextInt(48, 90);
            generatedCode.append((char) acciiCode);
        }

        return generatedCode.toString();
    }
}
