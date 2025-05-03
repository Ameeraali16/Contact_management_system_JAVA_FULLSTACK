package com._Pearls.contactsapp.contact_management_application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactEmailDTO {
    private Long id; // optional: for edit/delete
    private String email;
    private String label;
   
}
