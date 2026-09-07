package com.Easton.motordesk_backend.repository;

import com.Easton.motordesk_backend.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
   //If field = email, use instead of Id
   Optional<AppUser> findByEmail(String email);
}
