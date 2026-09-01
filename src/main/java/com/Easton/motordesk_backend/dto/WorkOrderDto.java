package com.Easton.motordesk_backend.dto;

import com.Easton.motordesk_backend.entity.WorkOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderDto {
    private Long id;
    private Long customerId;
    private Long vehicleId;
    private Long createdByUserId;
    private Long assignedTechnicianId;  // nullable — not assigned yet
    private Long shopId;
    private String description;
    private WorkOrder.Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}