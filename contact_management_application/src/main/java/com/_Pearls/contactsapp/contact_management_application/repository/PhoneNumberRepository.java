package com._Pearls.contactsapp.contact_management_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com._Pearls.contactsapp.contact_management_application.entity.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {

    
}