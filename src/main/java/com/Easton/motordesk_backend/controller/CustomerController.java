package com.Easton.motordesk_backend.controller;

import com.Easton.motordesk_backend.dto.CustomerDto;
import com.Easton.motordesk_backend.dto.ShopDto;
import com.Easton.motordesk_backend.entity.Customer;
import com.Easton.motordesk_backend.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController //Mark this class to handle HTTP req's
@RequestMapping("/api/motordesk/customer") //define base URL

public class CustomerController {

    private CustomerService customerService;

    //map POST request to create customer method
    @PostMapping
    ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto newCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<> (newCustomer, HttpStatus.CREATED);
    }

    //map GET request to get customer method
    @GetMapping("{id}")
    ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id){
        CustomerDto customerDto = customerService.getCustomerById(id);
        return ResponseEntity.ok(customerDto);
    }

    //map GET request to get all customers method
    @GetMapping
    ResponseEntity<List<CustomerDto>> getAllCustomers(){
        List<CustomerDto> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    //map PUT request to update customer method
    @PutMapping("{id}")
    ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto){
        CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    //map Delete request to delete customer method
    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
