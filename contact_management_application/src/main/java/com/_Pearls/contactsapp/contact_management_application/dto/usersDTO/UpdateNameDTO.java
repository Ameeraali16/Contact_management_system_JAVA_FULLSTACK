package com._Pearls.contactsapp.contact_management_application.dto.usersDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNameDTO {
    private String firstName;
    private String lastName;
}
