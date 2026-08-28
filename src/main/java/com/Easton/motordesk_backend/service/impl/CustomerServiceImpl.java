package com.Easton.motordesk_backend.service.impl;

import com.Easton.motordesk_backend.dto.CustomerDto;
import com.Easton.motordesk_backend.entity.Customer;
import com.Easton.motordesk_backend.entity.Shop;
import com.Easton.motordesk_backend.exception.ResourceNotFoundException;
import com.Easton.motordesk_backend.mapper.CustomerMapper;
import com.Easton.motordesk_backend.repository.CustomerRepository;
import com.Easton.motordesk_backend.repository.ShopRepository;
import com.Easton.motordesk_backend.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ShopRepository shopRepository;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {

        //use shopId from Dto to find customer entity for mapToEntity method
        Shop shop = shopRepository.findById(customerDto.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));

        //convert customer Dto to customer entity
        Customer customer = CustomerMapper.mapToCustomer(customerDto, shop);

        //save new customer
        Customer savedCustomer = customerRepository.save(customer);

        //return new customer to client
        return CustomerMapper.mapToCustomerDto(savedCustomer);
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {

        //find customer in db
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        //return customer entity from db as dto
        return CustomerMapper.mapToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();

        return customers.stream().map((customer) -> CustomerMapper
                        .mapToCustomerDto(customer)) // for EACH customer, run it through the Mapper (Entity → DTO)
                .collect(Collectors.toList()); // gather all the resulting DTOs back into a real List
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto updatedCustomer) {

        //use shopId from Dto to find customer entity for mapToEntity method
        Shop shop = shopRepository.findById(updatedCustomer.getShopId())
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));

        //validate customer exists
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        //update fields
        customer.setName(updatedCustomer.getName());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setAddress(updatedCustomer.getAddress());
        customer.setShop(shop);

        //save updates to customer entity in database
        Customer updatedCustomerObj = customerRepository.save(customer);

        //return updated customer as a dto
        return CustomerMapper.mapToCustomerDto(updatedCustomerObj);
    }

    @Override
    public void deleteCustomer(Long id) {

        //validate customer exists
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        //delete customer
        customerRepository.delete(customer);
    }
}
