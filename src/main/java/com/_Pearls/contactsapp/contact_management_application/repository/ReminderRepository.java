package com._Pearls.contactsapp.contact_management_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com._Pearls.contactsapp.contact_management_application.entity.ContactReminder;

public interface ReminderRepository extends JpaRepository<ContactReminder, Long>{
    
}
