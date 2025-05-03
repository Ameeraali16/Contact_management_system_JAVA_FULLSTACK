package com._Pearls.contactsapp.contact_management_application.mapper;

import com._Pearls.contactsapp.contact_management_application.dto.PhoneNumberDTO;
import com._Pearls.contactsapp.contact_management_application.entity.Contact;
import com._Pearls.contactsapp.contact_management_application.entity.PhoneNumber;

public class PhoneNumberMapper {

    // Map entity to DTO
    public static PhoneNumberDTO mapToPhoneNumberDTO(PhoneNumber phoneNumber) {
        return new PhoneNumberDTO(
                phoneNumber.getId(),
                phoneNumber.getPhoneNumber(),
                phoneNumber.getLabel()
        );
    }

    // Map DTO to entity
    public static PhoneNumber mapToPhoneNumber(PhoneNumberDTO dto, Contact contact) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(dto.getId());
        phoneNumber.setPhoneNumber(dto.getPhoneNumber());
        phoneNumber.setLabel(dto.getLabel());
        phoneNumber.setContact(contact); // set owning contact
        return phoneNumber;
    }
}
