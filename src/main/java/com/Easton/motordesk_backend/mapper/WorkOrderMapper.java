package com.Easton.motordesk_backend.mapper;

import com.Easton.motordesk_backend.dto.WorkOrderDto;
import com.Easton.motordesk_backend.entity.*;

public class WorkOrderMapper {

    public static WorkOrderDto mapToWorkOrderDto(WorkOrder workOrder){
        WorkOrderDto dto = new WorkOrderDto();
        dto.setId(workOrder.getId());
        dto.setCustomerId(workOrder.getCustomer().getId());
        dto.setVehicleId(workOrder.getVehicle().getId());
        dto.setCreatedByUserId(workOrder.getCreatedBy().getId());
        // assignedTechnician can be null — guard against NPE
        dto.setAssignedTechnicianId(
                workOrder.getAssignedTechnician() != null
                        ? workOrder.getAssignedTechnician().getId()
                        : null
        );
        dto.setShopId(workOrder.getShop().getId());
        dto.setDescription(workOrder.getDescription());
        dto.setStatus(workOrder.getStatus());
        dto.setCreatedAt(workOrder.getCreatedAt());
        dto.setUpdatedAt(workOrder.getUpdatedAt());
        return dto;
    }

    public static WorkOrder mapToWorkOrder(WorkOrderDto dto, Customer customer, Vehicle vehicle,
                                           AppUser createdBy, AppUser assignedTechnician, Shop shop){
        return new WorkOrder(
                dto.getId(),
                customer,
                vehicle,
                createdBy,
                assignedTechnician,   // can be null, that's fine
                shop,
                dto.getDescription(),
                dto.getStatus(),
                dto.getCreatedAt(),
                dto.getUpdatedAt()
        );
    }
}