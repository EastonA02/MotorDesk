package com.Easton.motordesk_backend.mapper;

import com.Easton.motordesk_backend.dto.VehicleDto;
import com.Easton.motordesk_backend.entity.Customer;
import com.Easton.motordesk_backend.entity.Shop;
import com.Easton.motordesk_backend.entity.Vehicle;

public class VehicleMapper {

    public static VehicleDto mapToVehicleDto(Vehicle vehicle){
        return new VehicleDto(
                vehicle.getId(),
                vehicle.getVin(),
                vehicle.getPlate(),

                //reference entity to get its id
                vehicle.getCustomer().getId(),
                vehicle.getShop().getId()
        );
    }

    public static Vehicle mapToVehicle(VehicleDto vehicleDto, Customer customer, Shop shop){
        return new Vehicle(
                vehicleDto.getId(),
                customer,
                shop,
                vehicleDto.getVin(),
                vehicleDto.getPlate()
        );
    }
}
