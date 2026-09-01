package com.Easton.motordesk_backend.controller;

import com.Easton.motordesk_backend.dto.VehicleDto;
import com.Easton.motordesk_backend.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController //Mark this class to handle HTTP req's
@RequestMapping("/api/motordesk/vehicle") //define base URL

public class VehicleController {
    private VehicleService vehicleService;

    //Connect POST req to create vehicle method
    @PostMapping
    ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto){
        VehicleDto newVehicle = vehicleService.createVehicle(vehicleDto);

        return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
    }

    //Connect GET req with Id to getVehicle method
    @GetMapping("{id}")
    ResponseEntity<VehicleDto> getVehicleById(@PathVariable("id") Long id){
        VehicleDto vehicle = vehicleService.getVehicleById(id);

        return ResponseEntity.ok(vehicle);
    }

    //Connect Get req to getAllVehicles method
    @GetMapping
    ResponseEntity<List<VehicleDto>> getAllVehicles(){
        List<VehicleDto> vehicles = vehicleService.getAllVehicles();

        return ResponseEntity.ok(vehicles);
    }

    //Connect PUT req to updateVehicle method
    @PutMapping("{id}")
    ResponseEntity<VehicleDto> updateVehicle(@PathVariable("id") Long id, @RequestBody VehicleDto vehicleDto){
        VehicleDto updatedVehicle = vehicleService.updateVehicle(id, vehicleDto);

        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteVehicle(@PathVariable("id") Long id){
        vehicleService.deleteVehicle(id);

        return ResponseEntity.noContent().build();
    }

}
