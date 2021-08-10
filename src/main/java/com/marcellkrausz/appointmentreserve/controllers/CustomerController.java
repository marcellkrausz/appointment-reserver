package com.marcellkrausz.appointmentreserve.controllers;

import com.marcellkrausz.appointmentreserve.converters.StringToLong;
import com.marcellkrausz.appointmentreserve.exceptions.CustomerNotFoundException;
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
    public Customer getById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CustomerNotFoundException("Must enter a valid number.");
        }
        return customerService.getCustomerById(StringToLong.convert(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@Valid @RequestBody CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
        return customerDto.getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody CustomerDto customerDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CustomerNotFoundException("Must enter a valid number.");
        }
        customerDto.setId(StringToLong.convert(id));
        customerService.saveCustomer(customerDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CustomerNotFoundException("Must enter a valid number.");
        }
        customerService.deleteCustomerById(StringToLong.convert(id));
    }
}
