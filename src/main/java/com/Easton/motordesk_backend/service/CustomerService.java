package com.Easton.motordesk_backend.service;

import com.Easton.motordesk_backend.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    //create customer
    CustomerDto createCustomer (CustomerDto customerDto);

    //fetch customer by id
    CustomerDto getCustomerById(Long customerId);

    //gives list of all customers
    List<CustomerDto> getAllCustomers();

    //update customer
    CustomerDto updateCustomer(Long id, CustomerDto updatedCustomer);

    //delete customer
    void deleteCustomer(Long id);
}
