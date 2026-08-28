package com.Easton.motordesk_backend.repository;

import com.Easton.motordesk_backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer, Long> {
}
