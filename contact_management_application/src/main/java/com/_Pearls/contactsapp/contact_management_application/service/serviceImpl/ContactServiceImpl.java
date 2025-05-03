package com._Pearls.contactsapp.contact_management_application.service.serviceImpl;

import com._Pearls.contactsapp.contact_management_application.dto.*;
import com._Pearls.contactsapp.contact_management_application.entity.*;
import com._Pearls.contactsapp.contact_management_application.exception.*;
import com._Pearls.contactsapp.contact_management_application.mapper.*;
import com._Pearls.contactsapp.contact_management_application.repository.*;
import com._Pearls.contactsapp.contact_management_application.service.ContactService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private NotesRepository contactNoteRepository;

    @Autowired
    private ReminderRepository contactReminderRepository;

    // ---------------- Contact ----------------

    @Override
    public ContactDTO createContact(ContactDTO contactDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.warn("User with ID {} not found while creating contact", userId);
                    return new ResourceNotFoundException("User not found");
                });

        Contact contact = ContactMapper.mapToContact(contactDTO, user);
        contact.setUser(user);

        Contact saved = contactRepository.save(contact);
        logger.info("Created new contact for user ID {}", userId);
        return ContactMapper.maptoContactDTO(saved);
    }

    @Override
    public void deleteContact(Long contactId, Long userId) {
        Contact contact = getValidContact(contactId, userId);
        contactRepository.delete(contact);
        logger.info("Deleted contact ID {} for user ID {}", contactId, userId);
    }

    @Override
    public ContactDTO updateContact(ContactDTO contactDTO, Long userId) {
        Contact contact = getValidContact(contactDTO.getId(), userId);

        contact.setFirstname(contactDTO.getFirstname());
        contact.setLastname(contactDTO.getLastname());
        contact.setTitle(contactDTO.getTitle());

        logger.info("Updated contact ID {} for user ID {}", contact.getId(), userId);
        return ContactMapper.maptoContactDTO(contactRepository.save(contact));
    }

    @Override
    public ContactDTO getContact(Long contactId, Long userId) {
        Contact contact = getValidContact(contactId, userId);
        return ContactMapper.maptoContactDTO(contact);
    }

    // ---------------- Phone Number ----------------

    @Override
    public ContactDTO addPhoneNumber(Long contactId, Long userId, PhoneNumberDTO dto) {
        Contact contact = getValidContact(contactId, userId);

        PhoneNumber phone = new PhoneNumber();
        phone.setPhoneNumber(dto.getPhoneNumber());
        phone.setContact(contact);

        if (contact.getPhoneNumbers() == null)
            contact.setPhoneNumbers(new ArrayList<>());

        contact.getPhoneNumbers().add(phone);
        logger.info("Added phone number to contact ID {}", contactId);
        return ContactMapper.maptoContactDTO(contactRepository.save(contact));
    }

    @Override
    public ContactDTO updatePhoneNumber(Long contactId, Long userId, PhoneNumberDTO dto) {
        Contact contact = getValidContact(contactId, userId);

        PhoneNumber phone = contact.getPhoneNumbers().stream()
                .filter(p -> p.getId().equals(dto.getId()))
                .findFirst()
                .orElseThrow(() -> {
                    logger.warn("Phone number ID {} not found for contact ID {}", dto.getId(), contactId);
                    return new PhoneNumberNotFoundException("Phone number not found");
                });

        phone.setPhoneNumber(dto.getPhoneNumber());
        logger.info("Updated phone number ID {} for contact ID {}", dto.getId(), contactId);
        return ContactMapper.maptoContactDTO(contactRepository.save(contact));
    }

    @Override
    public ContactDTO deletePhoneNumber(Long contactId, Long userId, Long phoneId) {
        Contact contact = getValidContact(contactId, userId);
        contact.getPhoneNumbers().removeIf(p -> p.getId().equals(phoneId));
        logger.info("Deleted phone number ID {} from contact ID {}", phoneId, contactId);
        return ContactMapper.maptoContactDTO(contactRepository.save(contact));
    }

    // ---------------- Email ----------------

    @Override
    public ContactDTO addEmail(Long contactId, Long userId, ContactEmailDTO dto) {
        Contact contact = getValidContact(contactId, userId);

        ContactEmail email = new ContactEmail();
        email.setEmailId(dto.getEmail());
        email.setContact(contact);

        if (contact.getEmails() == null)
            contact.setEmails(new ArrayList<>());

        contact.getEmails().add(email);
        logger.info("Added email to contact ID {}", contactId);
        return ContactMapper.maptoContactDTO(contactRepository.save(contact));
    }

    @Override
    public ContactDTO updateEmail(Long contactId, Long userId, ContactEmailDTO dto) {
        Contact contact = getValidContact(contactId, userId);

        ContactEmail email = contact.getEmails().stream()
                .filter(e -> e.getId().equals(dto.getId()))
                .findFirst()
                .orElseThrow(() -> {
                    logger.warn("Email ID {} not found for contact ID {}", dto.getId(), contactId);
                    return new EmailNotFoundException("Email not found");
                });

        email.setEmailId(dto.getEmail());
        logger.info("Updated email ID {} for contact ID {}", dto.getId(), contactId);
        return ContactMapper.maptoContactDTO(contactRepository.save(contact));
    }

    @Override
    public ContactDTO deleteEmail(Long contactId, Long userId, Long emailId) {
        Contact contact = getValidContact(contactId, userId);
        contact.getEmails().removeIf(e -> e.getId().equals(emailId));
        logger.info("Deleted email ID {} from contact ID {}", emailId, contactId);
        return ContactMapper.maptoContactDTO(contactRepository.save(contact));
    }

    // ---------------- Notes ----------------

    @Override
    public ContactNotesDTO addNote(Long contactId, Long userId, ContactNotesDTO dto) {
        Contact contact = getValidContact(contactId, userId);

        ContactNotes note = new ContactNotes();
        note.setNote(dto.getNote());
        note.setContact(contact);

        contactNoteRepository.save(note);
        logger.info("Added note to contact ID {}", contactId);
        return ContactNotesMapper.mapToDTO(note);
    }

    @Override
    public ContactNotesDTO updateNote(Long contactId, Long userId, ContactNotesDTO dto) {
        ContactNotes note = contactNoteRepository.findById(dto.getNoteId())
                .filter(n -> n.getContact().getId().equals(contactId))
                .orElseThrow(() -> {
                    logger.warn("Note ID {} not found for contact ID {}", dto.getNoteId(), contactId);
                    return new ResourceNotFoundException("Note not found");
                });

        note.setNote(dto.getNote());
        contactNoteRepository.save(note);
        logger.info("Updated note ID {} for contact ID {}", dto.getNoteId(), contactId);
        return ContactNotesMapper.mapToDTO(note);
    }

    @Override
    public void deleteNote(Long contactId, Long userId, Long noteId) {
        ContactNotes note = contactNoteRepository.findById(noteId)
                .filter(n -> n.getContact().getId().equals(contactId))
                .orElseThrow(() -> {
                    logger.warn("Note ID {} not found for contact ID {}", noteId, contactId);
                    return new ResourceNotFoundException("Note not found");
                });

        contactNoteRepository.delete(note);
        logger.info("Deleted note ID {} from contact ID {}", noteId, contactId);
    }

    // ---------------- Reminders ----------------

    @Override
    public ContactReminderDTO addReminder(Long contactId, Long userId, ContactReminderDTO dto) {
        Contact contact = getValidContact(contactId, userId);

        ContactReminder reminder = new ContactReminder();
        reminder.setReminderNote(dto.getReminderNote());
        reminder.setReminderTime(dto.getReminderTime());
        reminder.setContact(contact);

        contactReminderRepository.save(reminder);
        logger.info("Added reminder to contact ID {}", contactId);
        return ContactReminderMapper.mapToDTO(reminder);
    }

    @Override
    public ContactReminderDTO updateReminder(Long contactId, Long userId, ContactReminderDTO dto) {
        ContactReminder reminder = contactReminderRepository.findById(dto.getReminderId())
                .filter(r -> r.getContact().getId().equals(contactId))
                .orElseThrow(() -> {
                    logger.warn("Reminder ID {} not found for contact ID {}", dto.getReminderId(), contactId);
                    return new ResourceNotFoundException("Reminder not found");
                });

        reminder.setReminderNote(dto.getReminderNote());
        reminder.setReminderTime(dto.getReminderTime());
        contactReminderRepository.save(reminder);
        logger.info("Updated reminder ID {} for contact ID {}", dto.getReminderId(), contactId);
        return ContactReminderMapper.mapToDTO(reminder);
    }

    @Override
    public void deleteReminder(Long contactId, Long userId, Long reminderId) {
        ContactReminder reminder = contactReminderRepository.findById(reminderId)
                .filter(r -> r.getContact().getId().equals(contactId))
                .orElseThrow(() -> {
                    logger.warn("Reminder ID {} not found for contact ID {}", reminderId, contactId);
                    return new ResourceNotFoundException("Reminder not found");
                });

        contactReminderRepository.delete(reminder);
        logger.info("Deleted reminder ID {} from contact ID {}", reminderId, contactId);
    }

    // ---------------- Utility ----------------

    private Contact getValidContact(Long contactId, Long userId) {
        return contactRepository.findById(contactId)
                .filter(c -> c.getUser().getId().equals(userId))
                .orElseThrow(() -> {
                    logger.warn("Unauthorized access or contact not found: contact ID {}, user ID {}", contactId, userId);
                    return new ContactOwnershipException("Contact not found or not owned by user");
                });
    }
}
