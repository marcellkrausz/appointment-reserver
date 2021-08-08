package com.marcellkrausz.appointmentreserve.controllers;

import com.marcellkrausz.appointmentreserve.models.dto.CustomerDto;
import com.marcellkrausz.appointmentreserve.models.Customer;
import com.marcellkrausz.appointmentreserve.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public Set<Customer> getAll() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@Valid @RequestBody CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
        return customerDto.getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody CustomerDto customerDto, @PathVariable("id") Long id) {
        customerDto.setId(id);
        customerService.saveCustomer(customerDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        customerService.deleteCustomerById(id);
    }
}
