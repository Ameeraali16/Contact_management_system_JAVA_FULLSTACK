package com._Pearls.contactsapp.contact_management_application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberDTO {
    private Long id; // Needed for update/delete operations
    private String phoneNumber; 
    private String label;
}
