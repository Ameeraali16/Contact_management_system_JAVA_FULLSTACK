package com._Pearls.contactsapp.contact_management_application.mapper;

import com._Pearls.contactsapp.contact_management_application.dto.ContactReminderDTO;
import com._Pearls.contactsapp.contact_management_application.entity.ContactReminder;
import com._Pearls.contactsapp.contact_management_application.entity.Contact;
import com._Pearls.contactsapp.contact_management_application.entity.User;

public class ContactReminderMapper {

    // Entity → DTO
    public static ContactReminderDTO mapToDTO(ContactReminder reminder) {
        if (reminder == null) return null;

        return new ContactReminderDTO(
                reminder.getReminderId(),
                reminder.getContact() != null ? reminder.getContact().getId() : null,
                reminder.getReminderNote(),
                reminder.getReminderTime(),
                reminder.getStatus()
        );
    }

    // DTO → Entity
    public static ContactReminder mapToEntity(ContactReminderDTO dto, User user, Contact contact) {
        if (dto == null) return null;

        ContactReminder reminder = new ContactReminder();
        reminder.setReminderId(dto.getReminderId());
        reminder.setContact(contact);
        reminder.setReminderNote(dto.getReminderNote());
        reminder.setReminderTime(dto.getReminderTime());
        reminder.setStatus(dto.getStatus());
        return reminder;
    }
}
