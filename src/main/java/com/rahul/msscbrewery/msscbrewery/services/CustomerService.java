package com.rahul.msscbrewery.msscbrewery.services;

import com.rahul.msscbrewery.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

/**
 * Created by Rahul on 12/14/19
 */
public interface CustomerService {
    CustomerDto getCustomerById(UUID id);

    CustomerDto saveCustomer(CustomerDto customerDto);

    void updateCustomer(UUID customerId, CustomerDto customerDto);

    void deleteCustomer(UUID customerId);
}
