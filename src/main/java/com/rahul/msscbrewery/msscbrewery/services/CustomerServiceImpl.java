package com.rahul.msscbrewery.msscbrewery.services;

import com.rahul.msscbrewery.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Rahul on 12/14/19
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID id) {
        return CustomerDto.builder().id(id)
                .customerName("John Doe")
                .build();
    }
}
