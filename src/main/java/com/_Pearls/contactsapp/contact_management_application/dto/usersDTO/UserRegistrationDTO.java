package com._Pearls.contactsapp.contact_management_application.dto.usersDTO;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String firstName;
    private String lastName;
    private String authEmail;
    private String authPhoneNo;
    private String password;
    private String confirmPassword; 
}
