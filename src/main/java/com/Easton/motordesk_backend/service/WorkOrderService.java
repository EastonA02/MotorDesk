package com.Easton.motordesk_backend.service;

import com.Easton.motordesk_backend.dto.WorkOrderDto;

import java.util.List;

public interface WorkOrderService {
    WorkOrderDto createWorkOrder(WorkOrderDto workOrderDto);
    WorkOrderDto getWorkOrderById(Long id);
    List<WorkOrderDto> getAllWorkOrders();
    WorkOrderDto updateWorkOrder(Long id, WorkOrderDto updatedWorkOrder);
    void deleteWorkOrder(Long id);
}