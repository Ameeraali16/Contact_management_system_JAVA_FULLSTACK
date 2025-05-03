package com._Pearls.contactsapp.contact_management_application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com._Pearls.contactsapp.contact_management_application.dto.usersDTO.*;
import com._Pearls.contactsapp.contact_management_application.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // Add user ✅
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        logger.info("Received request to register user with email: {}", userRegistrationDTO.getAuthEmail());
        UserDTO savedUserDTO = userService.createUser(userRegistrationDTO);
        logger.info("User registered successfully with ID: {}", savedUserDTO.getId());
        return new ResponseEntity<>(savedUserDTO, HttpStatus.CREATED);
    }

    // Login user ✅
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        logger.info("Login attempt for identifier: {}", userLoginDTO.getAuthEmailOrPhone());
        String response = userService.loginUser(userLoginDTO);
        logger.info("Login successful for: {}", userLoginDTO.getAuthEmailOrPhone());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Update password ✅
    @PutMapping("/{userId}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long userId,
                                               @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        logger.info("Updating password for user ID: {}", userId);
        userService.updatePassword(userId, updatePasswordDTO);
        logger.info("Password updated successfully for user ID: {}", userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update profile picture ✅
    @PutMapping("/{userId}/profile-picture")
    public ResponseEntity<UserDTO> updateProfilePicture(@PathVariable Long userId,
                                                        @RequestBody UpdateProfilePictureDTO pictureDTO) {
        logger.info("Updating profile picture for user ID: {}", userId);
        UserDTO updatedUser = userService.updateProfilePicture(userId, pictureDTO);
        logger.info("Profile picture updated for user ID: {}", userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Update name ✅
    @PutMapping("/{userId}/name")
    public ResponseEntity<UserDTO> updateName(@PathVariable Long userId,
                                              @RequestBody UpdateNameDTO updateNameDTO) {
        logger.info("Updating name for user ID: {}", userId);
        UserDTO updatedUser = userService.updateName(userId, updateNameDTO);
        logger.info("Name updated for user ID: {}", userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Delete user ❌
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        logger.warn("Deleting user with ID: {}", userId);
        userService.deleteUser(userId);
        logger.info("User deleted successfully with ID: {}", userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get current user (needs JWT) ❌
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        logger.info("Fetching currently authenticated user");
        UserDTO userDTO = userService.getCurrentUser();
        logger.info("Current user retrieved: {}", userDTO.getId());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
