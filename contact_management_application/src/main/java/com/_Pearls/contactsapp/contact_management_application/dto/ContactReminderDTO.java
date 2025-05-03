package com._Pearls.contactsapp.contact_management_application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactReminderDTO {
    private Long reminderId; // for updates
    private Long contactId;
    private String reminderNote;
    private LocalDateTime reminderTime;
    private String status; // e.g., "active", "completed"
}
