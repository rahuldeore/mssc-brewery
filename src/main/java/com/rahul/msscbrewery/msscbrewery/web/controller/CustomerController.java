package com.rahul.msscbrewery.msscbrewery.web.controller;

import com.rahul.msscbrewery.msscbrewery.services.CustomerService;
import com.rahul.msscbrewery.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Created by Rahul on 12/14/19
 */
@RequestMapping("/customer/api/v1")
@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping ("/customer")
    public ResponseEntity saveNewCustomer(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto newCustomer = customerService.saveCustomer(customerDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/customer/api/v1/customer/" + newCustomer.getId());
        return new ResponseEntity(newCustomer, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/customer/{customerId}")
    public ResponseEntity insertUpdateCustomerById(@Valid @RequestBody CustomerDto customerDto,
                                                   @PathVariable("customerId") UUID customerId) {
        customerService.updateCustomer(customerId, customerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
        customerService.deleteCustomer(customerId);
    }

}
