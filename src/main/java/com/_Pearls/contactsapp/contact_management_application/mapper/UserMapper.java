package com._Pearls.contactsapp.contact_management_application.mapper;

import com._Pearls.contactsapp.contact_management_application.dto.usersDTO.UserDTO;
import com._Pearls.contactsapp.contact_management_application.dto.usersDTO.UserRegistrationDTO;
import com._Pearls.contactsapp.contact_management_application.entity.User;

public class UserMapper {

    // Entity → DTO
    public static UserDTO mapToUserDTO(User user) {
        if (user == null) return null;

        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getAuthEmail(),
                user.getAuthPhoneNo(),
                user.getProfileImage()
        );
    }
    // DTO → Entity (from UserDTO, no password)
    public static User mapToUser(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAuthEmail(dto.getAuthEmail());
        user.setAuthPhoneNo(dto.getAuthPhoneNo());
        user.setProfileImage(dto.getProfileImage());
        // passwordHash should be handled separately during registration/update
        return user;
    }

    // DTO → Entity (from registration DTO, with hashed password)
    public static User mapToUserFromRegistration(UserRegistrationDTO dto, String hashedPassword) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAuthEmail(dto.getAuthEmail());
        user.setAuthPhoneNo(dto.getAuthPhoneNo());
        user.setPasswordHash(hashedPassword); 
        return user;
    }
}
