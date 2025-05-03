package com._Pearls.contactsapp.contact_management_application.controller;

import com._Pearls.contactsapp.contact_management_application.dto.*;
import com._Pearls.contactsapp.contact_management_application.service.ContactService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactsController {

    private final ContactService contactService;
    private static final Logger logger = LoggerFactory.getLogger(ContactsController.class);

    // ---------------- Contact ----------------

    @PostMapping("/user/{userId}")
    public ContactDTO createContact(@PathVariable Long userId, @RequestBody ContactDTO contactDTO) {
        logger.info("POST /api/contacts/user/{} - Create contact", userId);
        return contactService.createContact(contactDTO, userId);
    }

    @PutMapping("/user/{userId}")
    public ContactDTO updateContact(@PathVariable Long userId, @RequestBody ContactDTO contactDTO) {
        logger.info("PUT /api/contacts/user/{} - Update contact ID {}", userId, contactDTO.getId());
        return contactService.updateContact(contactDTO, userId);
    }

    @GetMapping("/user/{userId}/{contactId}")
    public ContactDTO getContact(@PathVariable Long userId, @PathVariable Long contactId) {
        logger.info("GET /api/contacts/user/{}/{} - Get contact", userId, contactId);
        return contactService.getContact(contactId, userId);
    }

    @DeleteMapping("/user/{userId}/{contactId}")
    public void deleteContact(@PathVariable Long userId, @PathVariable Long contactId) {
        logger.info("DELETE /api/contacts/user/{}/{} - Delete contact", userId, contactId);
        contactService.deleteContact(contactId, userId);
    }

    // ---------------- Phone Numbers ----------------

    @PostMapping("/user/{userId}/{contactId}/phones")
    public ContactDTO addPhoneNumber(@PathVariable Long userId,
                                     @PathVariable Long contactId,
                                     @RequestBody PhoneNumberDTO phoneDTO) {
        logger.info("POST /api/contacts/user/{}/{}/phones - Add phone number", userId, contactId);
        return contactService.addPhoneNumber(contactId, userId, phoneDTO);
    }

    @PutMapping("/user/{userId}/{contactId}/phones")
    public ContactDTO updatePhoneNumber(@PathVariable Long userId,
                                        @PathVariable Long contactId,
                                        @RequestBody PhoneNumberDTO phoneDTO) {
        logger.info("PUT /api/contacts/user/{}/{}/phones - Update phone number ID {}", userId, contactId, phoneDTO.getId());
        return contactService.updatePhoneNumber(contactId, userId, phoneDTO);
    }

    @DeleteMapping("/user/{userId}/{contactId}/phones/{phoneId}")
    public ContactDTO deletePhoneNumber(@PathVariable Long userId,
                                        @PathVariable Long contactId,
                                        @PathVariable Long phoneId) {
        logger.info("DELETE /api/contacts/user/{}/{}/phones/{} - Delete phone number", userId, contactId, phoneId);
        return contactService.deletePhoneNumber(contactId, userId, phoneId);
    }

    // ---------------- Emails ----------------

    @PostMapping("/user/{userId}/{contactId}/emails")
    public ContactDTO addEmail(@PathVariable Long userId,
                               @PathVariable Long contactId,
                               @RequestBody ContactEmailDTO emailDTO) {
        logger.info("POST /api/contacts/user/{}/{}/emails - Add email", userId, contactId);
        return contactService.addEmail(contactId, userId, emailDTO);
    }

    @PutMapping("/user/{userId}/{contactId}/emails")
    public ContactDTO updateEmail(@PathVariable Long userId,
                                  @PathVariable Long contactId,
                                  @RequestBody ContactEmailDTO emailDTO) {
        logger.info("PUT /api/contacts/user/{}/{}/emails - Update email ID {}", userId, contactId, emailDTO.getId());
        return contactService.updateEmail(contactId, userId, emailDTO);
    }

    @DeleteMapping("/user/{userId}/{contactId}/emails/{emailId}")
    public ContactDTO deleteEmail(@PathVariable Long userId,
                                  @PathVariable Long contactId,
                                  @PathVariable Long emailId) {
        logger.info("DELETE /api/contacts/user/{}/{}/emails/{} - Delete email", userId, contactId, emailId);
        return contactService.deleteEmail(contactId, userId, emailId);
    }

    // ---------------- Notes ----------------

    @PostMapping("/user/{userId}/{contactId}/notes")
    public ContactNotesDTO addNote(@PathVariable Long userId,
                                   @PathVariable Long contactId,
                                   @RequestBody ContactNotesDTO noteDTO) {
        logger.info("POST /api/contacts/user/{}/{}/notes - Add note", userId, contactId);
        return contactService.addNote(contactId, userId, noteDTO);
    }

    @PutMapping("/user/{userId}/{contactId}/notes")
    public ContactNotesDTO updateNote(@PathVariable Long userId,
                                      @PathVariable Long contactId,
                                      @RequestBody ContactNotesDTO noteDTO) {
        logger.info("PUT /api/contacts/user/{}/{}/notes - Update note ID {}", userId, contactId, noteDTO.getNoteId());
        return contactService.updateNote(contactId, userId, noteDTO);
    }

    @DeleteMapping("/user/{userId}/{contactId}/notes/{noteId}")
    public void deleteNote(@PathVariable Long userId,
                           @PathVariable Long contactId,
                           @PathVariable Long noteId) {
        logger.info("DELETE /api/contacts/user/{}/{}/notes/{} - Delete note", userId, contactId, noteId);
        contactService.deleteNote(contactId, userId, noteId);
    }

    // ---------------- Reminders ----------------

    @PostMapping("/user/{userId}/{contactId}/reminders")
    public ContactReminderDTO addReminder(@PathVariable Long userId,
                                          @PathVariable Long contactId,
                                          @RequestBody ContactReminderDTO reminderDTO) {
        logger.info("POST /api/contacts/user/{}/{}/reminders - Add reminder", userId, contactId);
        return contactService.addReminder(contactId, userId, reminderDTO);
    }

    @PutMapping("/user/{userId}/{contactId}/reminders")
    public ContactReminderDTO updateReminder(@PathVariable Long userId,
                                             @PathVariable Long contactId,
                                             @RequestBody ContactReminderDTO reminderDTO) {
        logger.info("PUT /api/contacts/user/{}/{}/reminders - Update reminder ID {}", userId, contactId, reminderDTO.getReminderId());
        return contactService.updateReminder(contactId, userId, reminderDTO);
    }

    @DeleteMapping("/user/{userId}/{contactId}/reminders/{reminderId}")
    public void deleteReminder(@PathVariable Long userId,
                               @PathVariable Long contactId,
                               @PathVariable Long reminderId) {
        logger.info("DELETE /api/contacts/user/{}/{}/reminders/{} - Delete reminder", userId, contactId, reminderId);
        contactService.deleteReminder(contactId, userId, reminderId);
    }
}
