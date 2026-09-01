package com.Easton.motordesk_backend.service.impl;

import com.Easton.motordesk_backend.dto.WorkOrderDto;
import com.Easton.motordesk_backend.entity.*;
import com.Easton.motordesk_backend.exception.ResourceNotFoundException;
import com.Easton.motordesk_backend.mapper.WorkOrderMapper;
import com.Easton.motordesk_backend.repository.*;
import com.Easton.motordesk_backend.service.WorkOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorkOrderServiceImpl implements WorkOrderService {

    private WorkOrderRepository workOrderRepository;
    private CustomerRepository customerRepository;
    private VehicleRepository vehicleRepository;
    private AppUserRepository appUserRepository;
    private ShopRepository shopRepository;

    @Override
    public WorkOrderDto createWorkOrder(WorkOrderDto dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        AppUser createdBy = appUserRepository.findById(dto.getCreatedByUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Creator user not found"));
        Shop shop = shopRepository.findById(dto.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));

        // assignedTechnician is optional — only look it up if an id was actually provided
        AppUser assignedTechnician = null;
        if (dto.getAssignedTechnicianId() != null) {
            assignedTechnician = appUserRepository.findById(dto.getAssignedTechnicianId())
                    .orElseThrow(() -> new ResourceNotFoundException("Technician not found"));
        }

        WorkOrder workOrder = WorkOrderMapper.mapToWorkOrder(dto, customer, vehicle, createdBy, assignedTechnician, shop);
        WorkOrder saved = workOrderRepository.save(workOrder);
        return WorkOrderMapper.mapToWorkOrderDto(saved);
    }

    @Override
    public WorkOrderDto getWorkOrderById(Long id) {
        WorkOrder workOrder = workOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work order not found"));
        return WorkOrderMapper.mapToWorkOrderDto(workOrder);
    }

    @Override
    public List<WorkOrderDto> getAllWorkOrders() {
        return workOrderRepository.findAll().stream()
                .map(WorkOrderMapper::mapToWorkOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public WorkOrderDto updateWorkOrder(Long id, WorkOrderDto updatedWorkOrder) {
        WorkOrder workOrder = workOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work order not found"));

        Customer customer = customerRepository.findById(updatedWorkOrder.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        Vehicle vehicle = vehicleRepository.findById(updatedWorkOrder.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        AppUser createdBy = appUserRepository.findById(updatedWorkOrder.getCreatedByUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Creator user not found"));
        Shop shop = shopRepository.findById(updatedWorkOrder.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));

        AppUser assignedTechnician = null;
        if (updatedWorkOrder.getAssignedTechnicianId() != null) {
            assignedTechnician = appUserRepository.findById(updatedWorkOrder.getAssignedTechnicianId())
                    .orElseThrow(() -> new ResourceNotFoundException("Technician not found"));
        }

        workOrder.setCustomer(customer);
        workOrder.setVehicle(vehicle);
        workOrder.setCreatedBy(createdBy);
        workOrder.setAssignedTechnician(assignedTechnician);
        workOrder.setShop(shop);
        workOrder.setDescription(updatedWorkOrder.getDescription());
        workOrder.setStatus(updatedWorkOrder.getStatus());
        // createdAt/updatedAt handled automatically by @PrePersist/@PreUpdate — don't touch them here

        WorkOrder saved = workOrderRepository.save(workOrder);
        return WorkOrderMapper.mapToWorkOrderDto(saved);
    }

    @Override
    public void deleteWorkOrder(Long id) {
        WorkOrder workOrder = workOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work order not found"));
        workOrderRepository.delete(workOrder);
    }
}