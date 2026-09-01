package com.Easton.motordesk_backend.service.impl;

import com.Easton.motordesk_backend.dto.VehicleDto;
import com.Easton.motordesk_backend.entity.Customer;
import com.Easton.motordesk_backend.entity.Shop;
import com.Easton.motordesk_backend.entity.Vehicle;
import com.Easton.motordesk_backend.exception.ResourceNotFoundException;
import com.Easton.motordesk_backend.mapper.VehicleMapper;
import com.Easton.motordesk_backend.repository.CustomerRepository;
import com.Easton.motordesk_backend.repository.ShopRepository;
import com.Easton.motordesk_backend.repository.VehicleRepository;
import com.Easton.motordesk_backend.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;
    private CustomerRepository customerRepository;
    private ShopRepository shopRepository;

    @Override
    public VehicleDto createVehicle(VehicleDto vehicleDto) {

        //get customer entity for Dto
        Customer customer = customerRepository.findById(vehicleDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("customer not found"));

        //get shop entity for Dto
        Shop shop = shopRepository.findById(vehicleDto.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("shop not found"));

        Vehicle vehicle = VehicleMapper.mapToVehicle(vehicleDto, customer, shop);

        Vehicle newVehicle = vehicleRepository.save(vehicle);

        return VehicleMapper.mapToVehicleDto(newVehicle);
    }

    @Override
    public VehicleDto getVehicleById(Long vehicleId) {

        //validate vehicle exists
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found"));

        return VehicleMapper.mapToVehicleDto(vehicle);
    }

    @Override
    public List<VehicleDto> getAllVehicles() {

        List<Vehicle> vehicles = vehicleRepository.findAll();

        return vehicles.stream().map((vehicle) -> VehicleMapper
                        .mapToVehicleDto(vehicle)) // for EACH customer, run it through the Mapper (Entity → DTO)
                .collect(Collectors.toList()); // gather all the resulting DTOs back into a real List
    }

    @Override
    public VehicleDto updateVehicle(Long id, VehicleDto updatedVehicle) {

        //validate vehicle exists
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found"));

        //get shop entity for mapper
        Shop shop = shopRepository.findById(updatedVehicle.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("shop not found"));

        //get customer entity for Dto
        Customer customer = customerRepository.findById(updatedVehicle.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("customer not found"));

        vehicle.setShop(shop);
        vehicle.setVin(updatedVehicle.getVin());
        vehicle.setCustomer(customer);
        vehicle.setPlate(updatedVehicle.getPlate());

        Vehicle newVehicleObj = vehicleRepository.save(vehicle);

        return VehicleMapper.mapToVehicleDto(newVehicleObj);
    }

    @Override
    public void deleteVehicle(Long id) {

        //validate vehicle exists
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("vehicle not found"));

        vehicleRepository.delete(vehicle);

    }
}
