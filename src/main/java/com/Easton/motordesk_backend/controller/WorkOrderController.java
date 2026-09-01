package com.Easton.motordesk_backend.controller;

import com.Easton.motordesk_backend.dto.WorkOrderDto;
import com.Easton.motordesk_backend.service.WorkOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/motordesk/workorder")
public class WorkOrderController {

    private WorkOrderService workOrderService;

    @PostMapping
    public ResponseEntity<WorkOrderDto> createWorkOrder(@RequestBody WorkOrderDto dto){
        WorkOrderDto newWorkOrder = workOrderService.createWorkOrder(dto);
        return new ResponseEntity<>(newWorkOrder, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkOrderDto> getWorkOrderById(@PathVariable("id") Long id){
        return ResponseEntity.ok(workOrderService.getWorkOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<WorkOrderDto>> getAllWorkOrders(){
        return ResponseEntity.ok(workOrderService.getAllWorkOrders());
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkOrderDto> updateWorkOrder(@PathVariable("id") Long id, @RequestBody WorkOrderDto dto){
        return ResponseEntity.ok(workOrderService.updateWorkOrder(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteWorkOrder(@PathVariable("id") Long id){
        workOrderService.deleteWorkOrder(id);
        return ResponseEntity.noContent().build();
    }
}