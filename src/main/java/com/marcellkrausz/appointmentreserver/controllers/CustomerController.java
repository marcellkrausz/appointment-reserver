package com.marcellkrausz.appointmentreserver.controllers;

import com.marcellkrausz.appointmentreserver.models.dto.CustomerDto;
import com.marcellkrausz.appointmentreserver.models.Customer;
import com.marcellkrausz.appointmentreserver.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public Set<Customer> getAll() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/customer/{id}")
    public Customer getById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customer")
    public void save(@RequestBody CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
    }

    @PutMapping("/customer/{id}")
    public void update(@RequestBody CustomerDto customerDto,  @PathVariable("id") Long id) {
        customerDto.setId(id);
        customerService.saveCustomer(customerDto);
    }

    @DeleteMapping("/customer/{id}")
    public void delete(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
    }
}
