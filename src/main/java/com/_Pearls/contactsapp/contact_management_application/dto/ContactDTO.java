package com._Pearls.contactsapp.contact_management_application.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private Long id; // For updates
    private String firstname;
    private String lastname;
    private String title;
    private List<PhoneNumberDTO> phoneNumbers;
    private List<ContactEmailDTO> emails;
    private List<ContactNotesDTO> notes;
    private List<ContactReminderDTO> reminders;
}
