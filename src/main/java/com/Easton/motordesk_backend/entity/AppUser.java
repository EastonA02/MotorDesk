package com.Easton.motordesk_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "app_user")

@Entity
public class AppUser {

    //Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;

    //Foreign Key
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    //All other attributes

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "email", nullable = false, unique = true,
    length = 150)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password;

    //Enums for roles
    public enum UserRole {
        ADVISOR,
        TECHNICIAN,
        ADMIN
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;
}
