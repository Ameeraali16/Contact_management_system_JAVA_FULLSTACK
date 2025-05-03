package com._Pearls.contactsapp.contact_management_application.mapper;

import com._Pearls.contactsapp.contact_management_application.dto.ContactNotesDTO;
import com._Pearls.contactsapp.contact_management_application.entity.ContactNotes;
import com._Pearls.contactsapp.contact_management_application.entity.Contact;
import com._Pearls.contactsapp.contact_management_application.entity.User;

public class ContactNotesMapper {

    // Entity → DTO
    public static ContactNotesDTO mapToDTO(ContactNotes note) {
        if (note == null) return null;

        return new ContactNotesDTO(
                note.getNoteId(),
                note.getContact() != null ? note.getContact().getId() : null,
                note.getNote()
              
        );
    }

    // DTO → Entity
    public static ContactNotes mapToEntity(ContactNotesDTO dto, User user, Contact contact) {
        if (dto == null) return null;

        ContactNotes note = new ContactNotes();
        note.setNoteId(dto.getNoteId());
            // user & contact are passed from service/controller
        note.setContact(contact);
        note.setNote(dto.getNote());

        return note;
    }
}
