package com.Easton.motordesk_backend.service;

import com.Easton.motordesk_backend.dto.VehicleDto;

import java.util.List;

public interface VehicleService {

    //create vehicle
    VehicleDto createVehicle (VehicleDto vehicleDto);

    //fetch vehicle by id
    VehicleDto getVehicleById(Long vehicleId);

    //gives list of all vehicles
    List<VehicleDto> getAllVehicles();

    //update vehicle
    VehicleDto updateVehicle(Long id, VehicleDto updatedVehicle);

    //delete vehicle
    void deleteVehicle(Long id);
}
