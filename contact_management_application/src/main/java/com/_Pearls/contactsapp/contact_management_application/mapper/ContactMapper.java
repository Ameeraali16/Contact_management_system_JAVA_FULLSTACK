package com._Pearls.contactsapp.contact_management_application.mapper;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com._Pearls.contactsapp.contact_management_application.dto.ContactDTO;
import com._Pearls.contactsapp.contact_management_application.dto.ContactEmailDTO;
import com._Pearls.contactsapp.contact_management_application.dto.ContactNotesDTO;
import com._Pearls.contactsapp.contact_management_application.dto.ContactReminderDTO;
import com._Pearls.contactsapp.contact_management_application.dto.PhoneNumberDTO;
import com._Pearls.contactsapp.contact_management_application.entity.Contact;
import com._Pearls.contactsapp.contact_management_application.entity.ContactEmail;
import com._Pearls.contactsapp.contact_management_application.entity.ContactNotes;
import com._Pearls.contactsapp.contact_management_application.entity.ContactReminder;
import com._Pearls.contactsapp.contact_management_application.entity.PhoneNumber;
import com._Pearls.contactsapp.contact_management_application.entity.User;

public class ContactMapper {
    
  public static ContactDTO maptoContactDTO(Contact contact) {
    // Map PhoneNumber entities to PhoneNumberDTOs
    List<PhoneNumberDTO> phoneNumberDTOs = contact.getPhoneNumbers().stream()
            .map(phoneNumber -> new PhoneNumberDTO(
                    phoneNumber.getId(),          
                    phoneNumber.getPhoneNumber(), 
                    phoneNumber.getLabel()        
            ))
            .collect(Collectors.toList());

    // Map ContactEmail entities to ContactEmailDTOs
    List<ContactEmailDTO> contactEmailDTOs = contact.getEmails().stream()
            .map(contactEmail -> new ContactEmailDTO(
                    contactEmail.getId(),     
                    contactEmail.getEmailId(), 
                    contactEmail.getLabel()    
            ))
            .collect(Collectors.toList());

        List<ContactNotesDTO> contactNotesDTOs = contact.getNotes().stream()
            .map(note -> new ContactNotesDTO(
                note.getNoteId(),
                contact.getId(), // or note.getContact().getId() if loaded
                note.getNote()
            ))
            .collect(Collectors.toList());
        
        

            List<ContactReminderDTO> contactRemindersDTOs = contact.getReminders().stream()
            .map(reminder -> new ContactReminderDTO(
                reminder.getReminderId(),
                contact.getId(), // or reminder.getContact().getId()
                reminder.getReminderNote(),
                reminder.getReminderTime(),
                reminder.getStatus()
            ))
            .collect(Collectors.toList());
        

    return new ContactDTO(
            contact.getId(),
            contact.getFirstname(),
            contact.getLastname(),
            contact.getTitle(),
            phoneNumberDTOs,
            contactEmailDTOs,
            contactNotesDTOs,
            contactRemindersDTOs
    );
}

public static Contact mapToContact(ContactDTO contactDTO, User user) {
        Contact contact = new Contact();
        contact.setId(contactDTO.getId());
        contact.setFirstname(contactDTO.getFirstname());
        contact.setLastname(contactDTO.getLastname());
        contact.setTitle(contactDTO.getTitle());
    
     
        contact.setUser(user);
    
        List<PhoneNumber> phoneNumbers = contactDTO.getPhoneNumbers().stream()
                .map(dto -> {
                    PhoneNumber phone = new PhoneNumber();
                    phone.setId(dto.getId());
                    phone.setPhoneNumber(dto.getPhoneNumber());
                    phone.setLabel(dto.getLabel());
                    phone.setContact(contact); // Link to parent contact
                    return phone;
                })
                .collect(Collectors.toList());
    
        List<ContactEmail> emails = contactDTO.getEmails().stream()
                .map(dto -> {
                    ContactEmail email = new ContactEmail();
                    email.setId(dto.getId());
                    email.setEmailId(dto.getEmail());
                    email.setLabel(dto.getLabel());
                    email.setContact(contact); // Link to parent contact
                    return email;
                })
                .collect(Collectors.toList());

        List<ContactReminder> reminders = contactDTO.getReminders().stream()
        .map(dto -> {
                ContactReminder reminder = new ContactReminder();
                reminder.setReminderId(dto.getReminderId());
                reminder.setReminderNote(dto.getReminderNote());
                reminder.setReminderTime(dto.getReminderTime());
                reminder.setStatus(dto.getStatus());
                reminder.setContact(contact);
                return reminder;
        }) .collect(Collectors.toList());

        List<ContactNotes> notes = contactDTO.getNotes().stream()
        .map(dto -> {
                ContactNotes note = new ContactNotes();
                note.setContact(contact);
                note.setNote(dto.getNote());
                note.setNoteId(dto.getNoteId());
                
                return note;
        }).collect(Collectors.toList());
    
        contact.setPhoneNumbers(phoneNumbers);
        contact.setEmails(emails);
        contact.setReminders(reminders);
        contact.setNotes(notes);
    
        return contact;
    }
    
}
