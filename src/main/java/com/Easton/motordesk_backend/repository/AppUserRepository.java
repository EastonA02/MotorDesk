package com.Easton.motordesk_backend.repository;

import com.Easton.motordesk_backend.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
