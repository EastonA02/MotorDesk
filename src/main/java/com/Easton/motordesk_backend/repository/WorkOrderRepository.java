package com.Easton.motordesk_backend.repository;

import com.Easton.motordesk_backend.entity.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
}