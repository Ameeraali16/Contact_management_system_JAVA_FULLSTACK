package com._Pearls.contactsapp.contact_management_application.service;


import com._Pearls.contactsapp.contact_management_application.dto.usersDTO.UpdateNameDTO;
import com._Pearls.contactsapp.contact_management_application.dto.usersDTO.UpdatePasswordDTO;
import com._Pearls.contactsapp.contact_management_application.dto.usersDTO.UpdateProfilePictureDTO;
import com._Pearls.contactsapp.contact_management_application.dto.usersDTO.UserDTO;
import com._Pearls.contactsapp.contact_management_application.dto.usersDTO.UserLoginDTO;
import com._Pearls.contactsapp.contact_management_application.dto.usersDTO.UserRegistrationDTO;

public interface UserService {  

    //Create a User
    UserDTO createUser(UserRegistrationDTO userRegistrationDTO);

    //Login a User
    String loginUser(UserLoginDTO loginDTO);

    //Update Password for a User
    void updatePassword(Long userId, UpdatePasswordDTO passwordDTO);

    //Update ProfilePic Link for a user
    UserDTO updateProfilePicture(Long userId, UpdateProfilePictureDTO pictureDTO);

    //Update name for a User
    UserDTO updateName(Long userId, UpdateNameDTO nameDTO);

    //Delete a User
    void deleteUser(Long userId);

    //Get current User
    UserDTO getCurrentUser();
}
