package com._Pearls.contactsapp.contact_management_application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactNotesDTO {
    private Long noteId; // for updates
    private Long contactId; // for linking
    private String note;
}
