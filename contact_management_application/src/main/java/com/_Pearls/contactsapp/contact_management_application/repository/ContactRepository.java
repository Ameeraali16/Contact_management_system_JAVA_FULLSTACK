package com._Pearls.contactsapp.contact_management_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com._Pearls.contactsapp.contact_management_application.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    
}