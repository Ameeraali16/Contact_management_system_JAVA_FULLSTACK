package com._Pearls.contactsapp.contact_management_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com._Pearls.contactsapp.contact_management_application.entity.ContactEmail;

public interface EmailRepository extends JpaRepository<ContactEmail, Long> {
    
}