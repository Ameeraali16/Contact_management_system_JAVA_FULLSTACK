package com._Pearls.contactsapp.contact_management_application.mapper;

import com._Pearls.contactsapp.contact_management_application.dto.ContactEmailDTO;
import com._Pearls.contactsapp.contact_management_application.entity.Contact;
import com._Pearls.contactsapp.contact_management_application.entity.ContactEmail;

public class ContactEmailMapper {

    // Map entity to DTO
    public static ContactEmailDTO mapToContactEmailDTO(ContactEmail email) {
        return new ContactEmailDTO(
                email.getId(),
                email.getEmailId(),
                email.getLabel()
        );
    }

    // Map DTO to entity
    public static ContactEmail mapToContactEmail(ContactEmailDTO dto, Contact contact) {
        ContactEmail email = new ContactEmail();
        email.setId(dto.getId());
        email.setEmailId(dto.getEmail());
        email.setLabel(dto.getLabel());
        email.setContact(contact); // set owning contact
        return email;
    }
}
