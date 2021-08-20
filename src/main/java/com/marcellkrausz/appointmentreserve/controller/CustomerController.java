package com.marcellkrausz.appointmentreserve.controller;

import com.marcellkrausz.appointmentreserve.converter.StringToLong;
import com.marcellkrausz.appointmentreserve.exception.CustomerNotFoundException;
import com.marcellkrausz.appointmentreserve.model.Customer;
import com.marcellkrausz.appointmentreserve.model.dto.CustomerDto;
import com.marcellkrausz.appointmentreserve.service.CustomerService;
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
    public Set<CustomerDto> getAll() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    public CustomerDto getById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CustomerNotFoundException("Must enter a valid number.");
        }
        return customerService.getCustomerById(Long.valueOf(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto save(@Valid @RequestBody CustomerDto customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody CustomerDto customerDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CustomerNotFoundException("Must enter a valid number.");
        }
        customerDto.setId(Long.valueOf(id));
        customerService.saveCustomer(customerDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CustomerNotFoundException("Must enter a valid number.");
        }
        customerService.deleteCustomerById(Long.valueOf(id));
    }
}
