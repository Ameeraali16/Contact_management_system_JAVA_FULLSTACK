package com._Pearls.contactsapp.contact_management_application.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") 
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  

    @Column(name = "first_name", nullable = false)
    private String firstName;  

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "auth_email", unique = true)
    private String authEmail;

    @Column(name = "auth_phoneNo" , unique = true)
    private String authPhoneNo;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "profile_image")
    private String profileImage;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
