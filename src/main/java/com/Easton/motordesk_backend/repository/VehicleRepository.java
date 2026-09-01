package com.Easton.motordesk_backend.repository;

import com.Easton.motordesk_backend.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
