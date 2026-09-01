package com.Easton.motordesk_backend.mapper;

import com.Easton.motordesk_backend.dto.CustomerDto;
import com.Easton.motordesk_backend.entity.Customer;
import com.Easton.motordesk_backend.entity.Shop;

public class CustomerMapper {

    //map Customer entity to Customer Dto
    public static CustomerDto mapToCustomerDto(Customer customer){
        return new CustomerDto(
                customer.getId(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getName(),
                customer.getShop().getId()
        );
    }

    //map Customer Dto to Customer entity
    public static Customer mapToCustomer (CustomerDto customerDto, Shop shop){
        return new Customer(
                //Customer constructor expects Shop object --> (id, phone, address, name, shop)
                customerDto.getId(),
                customerDto.getPhone(),
                customerDto.getAddress(),
                customerDto.getName(),
                shop
        );
    }
}
