package com._Pearls.contactsapp.contact_management_application.service;

import com._Pearls.contactsapp.contact_management_application.dto.ContactDTO;
import com._Pearls.contactsapp.contact_management_application.dto.ContactEmailDTO;
import com._Pearls.contactsapp.contact_management_application.dto.ContactNotesDTO;
import com._Pearls.contactsapp.contact_management_application.dto.ContactReminderDTO;
import com._Pearls.contactsapp.contact_management_application.dto.PhoneNumberDTO;

public interface ContactService {

    // Create a contact for a user
    ContactDTO createContact(ContactDTO contactDTO, Long userId);

    // Delete a contact by contact ID and user ID (to verify ownership)
    void deleteContact(Long contactId, Long userId);

    // Update contact information (assumes DTO includes contact ID)
    ContactDTO updateContact(ContactDTO contactDTO, Long userId);

    // Get a specific contact by ID and user ID
    ContactDTO getContact(Long contactId, Long userId);

    // Add a phone number to a contact
    ContactDTO addPhoneNumber(Long contactId, Long userId, PhoneNumberDTO phoneNumberDTO);

    // Add an email to a contact
    ContactDTO addEmail(Long contactId, Long userId, ContactEmailDTO contactEmailDTO);

    // Update a phone number (needs phone number ID inside DTO)
    ContactDTO updatePhoneNumber(Long contactId, Long userId, PhoneNumberDTO phoneNumberDTO);

    // Update an email
    ContactDTO updateEmail(Long contactId, Long userId, ContactEmailDTO contactEmailDTO);

    // Delete a phone number by its ID
    ContactDTO deletePhoneNumber(Long contactId, Long userId, Long phoneNumberId);

    // Delete an email by its ID
    ContactDTO deleteEmail(Long contactId, Long userId, Long emailId);

    // Add a note to a contact
    ContactNotesDTO addNote(Long contactId, Long userId, ContactNotesDTO notesDTO);

    // Update a note
    ContactNotesDTO updateNote(Long contactId, Long userId, ContactNotesDTO notesDTO);

    // Delete a note
    void deleteNote(Long contactId, Long userId, Long noteId);

    // Add a reminder
    ContactReminderDTO addReminder(Long contactId, Long userId, ContactReminderDTO reminderDTO);

    // Update a reminder
    ContactReminderDTO updateReminder(Long contactId, Long userId, ContactReminderDTO reminderDTO);

    // Delete a reminder
    void deleteReminder(Long contactId, Long userId, Long reminderId);
}
