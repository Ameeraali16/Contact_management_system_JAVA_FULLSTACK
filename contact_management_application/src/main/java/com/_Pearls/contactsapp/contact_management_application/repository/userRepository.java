package com._Pearls.contactsapp.contact_management_application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com._Pearls.contactsapp.contact_management_application.entity.User;

public interface userRepository extends JpaRepository<User, Long>{

    Optional<User> findByAuthEmail(String authEmail);

    Optional<User> findByAuthPhoneNo(String authPhoneNo);
    
}
