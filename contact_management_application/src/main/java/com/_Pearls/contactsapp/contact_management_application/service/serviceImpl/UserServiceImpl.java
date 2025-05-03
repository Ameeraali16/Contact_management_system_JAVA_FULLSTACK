package com._Pearls.contactsapp.contact_management_application.service.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com._Pearls.contactsapp.contact_management_application.dto.usersDTO.*;
import com._Pearls.contactsapp.contact_management_application.entity.User;
import com._Pearls.contactsapp.contact_management_application.exception.InvalidCredentialsException;
import com._Pearls.contactsapp.contact_management_application.exception.UserAlreadyExistsException;
import com._Pearls.contactsapp.contact_management_application.mapper.UserMapper;
import com._Pearls.contactsapp.contact_management_application.repository.userRepository;
import com._Pearls.contactsapp.contact_management_application.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private userRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDTO createUser(UserRegistrationDTO userRegistrationDTO) {
        logger.info("Attempting to create user with email: {}", userRegistrationDTO.getAuthEmail());

        if (userRepository.findByAuthEmail(userRegistrationDTO.getAuthEmail()).isPresent()) {
            logger.warn("Email already registered: {}", userRegistrationDTO.getAuthEmail());
            throw new UserAlreadyExistsException("Email already registered");
        }

        if (userRepository.findByAuthPhoneNo(userRegistrationDTO.getAuthPhoneNo()).isPresent()) {
            logger.warn("Phone number already registered: {}", userRegistrationDTO.getAuthPhoneNo());
            throw new UserAlreadyExistsException("Phone number already registered");
        }

        String hashedPassword = passwordEncoder.encode(userRegistrationDTO.getConfirmPassword());
        User user = UserMapper.mapToUserFromRegistration(userRegistrationDTO, hashedPassword);
        User savedUser = userRepository.save(user);
        
        logger.info("User created successfully with ID: {}", savedUser.getId());
        return UserMapper.mapToUserDTO(savedUser);
    }

   @Override
public String loginUser(UserLoginDTO loginDTO) {
    String identifier = loginDTO.getAuthEmailOrPhone();
    logger.info("User attempting to log in with identifier: {}", identifier);

    Optional<User> userOptional;

    if (identifier.contains("@")) {
        userOptional = userRepository.findByAuthEmail(identifier);
    } else {
        userOptional = userRepository.findByAuthPhoneNo(identifier);
    }

    if (userOptional.isEmpty()) {
        logger.warn("Login failed: user not found for identifier {}", identifier);
        throw new InvalidCredentialsException("User does not exist.");
    }

    User user = userOptional.get();

    if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPasswordHash())) {
        logger.warn("Login failed: invalid password for user ID {}", user.getId());
        throw new InvalidCredentialsException("Invalid email/phone or password.");
    }

    logger.info("User login successful for ID: {}", user.getId());
    return "Login successful!";
}


    @Override
    public void updatePassword(Long userId, UpdatePasswordDTO passwordDTO) {
        logger.info("Attempting to update password for user ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", userId);
                    return new RuntimeException("User not found with ID: " + userId);
                });

        if (!passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPasswordHash())) {
            logger.warn("Password update failed: incorrect old password for user ID {}", userId);
            throw new RuntimeException("Old password is incorrect.");
        }

        user.setPasswordHash(passwordEncoder.encode(passwordDTO.getNewPassword()));
        userRepository.save(user);

        logger.info("Password updated successfully for user ID: {}", userId);
    }

    @Override
    public UserDTO updateProfilePicture(Long userId, UpdateProfilePictureDTO pictureDTO) {
        logger.info("Updating profile picture for user ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", userId);
                    return new RuntimeException("User not found with ID: " + userId);
                });

        user.setProfileImage(pictureDTO.getProfileImageUrl());
        User updatedUser = userRepository.save(user);

        logger.info("Profile picture updated successfully for user ID: {}", userId);
        return UserMapper.mapToUserDTO(updatedUser);
    }

    @Override
    public UserDTO updateName(Long userId, UpdateNameDTO nameDTO) {
        logger.info("Updating name for user ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", userId);
                    return new RuntimeException("User not found with ID: " + userId);
                });

        user.setFirstName(nameDTO.getFirstName());
        user.setLastName(nameDTO.getLastName());
        User updatedUser = userRepository.save(user);

        logger.info("Name updated successfully for user ID: {}", userId);
        return UserMapper.mapToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        logger.info("Attempting to delete user with ID: {}", userId);

        if (!userRepository.existsById(userId)) {
            logger.warn("Delete failed: user not found with ID: {}", userId);
            throw new RuntimeException("User not found with ID: " + userId);
        }

        userRepository.deleteById(userId);
        logger.info("User deleted successfully with ID: {}", userId);
    }

    @Override
    public UserDTO getCurrentUser() {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("Fetching current user with email: {}", currentUserEmail);

        User user = userRepository.findByAuthEmail(currentUserEmail)
                .orElseThrow(() -> {
                    logger.error("User not found with email: {}", currentUserEmail);
                    return new RuntimeException("User not found with email: " + currentUserEmail);
                });

        logger.info("Fetched current user ID: {}", user.getId());
        return UserMapper.mapToUserDTO(user);
    }
}
