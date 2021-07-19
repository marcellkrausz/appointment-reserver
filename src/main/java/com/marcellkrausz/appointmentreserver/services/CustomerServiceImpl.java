package com.marcellkrausz.appointmentreserver.services;

import com.marcellkrausz.appointmentreserver.dto.CustomerDto;
import com.marcellkrausz.appointmentreserver.converters.CustomerDtoToCustomer;
import com.marcellkrausz.appointmentreserver.converters.CustomerToCustomerDto;
import com.marcellkrausz.appointmentreserver.models.Customer;
import com.marcellkrausz.appointmentreserver.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerToCustomerDto customerToCustomerDto;
    private final CustomerDtoToCustomer customerDtoToCustomer;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerToCustomerDto customerToCustomerDto, CustomerDtoToCustomer customerDtoToCustomer) {
        this.customerRepository = customerRepository;
        this.customerToCustomerDto = customerToCustomerDto;
        this.customerDtoToCustomer = customerDtoToCustomer;
    }

    @Override
    public Set<Customer> getAllCustomer() {
        Set<Customer> customers = new HashSet<>();
        customerRepository.findAll().iterator().forEachRemaining(customers::add);
        return customers;
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }
        return customerOptional.get();
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer detachedCustomer = customerDtoToCustomer.convert(customerDto);

        Customer savedCustomer = customerRepository.save(detachedCustomer);
        log.debug("Saved Customer id: " + savedCustomer.getId());
        return customerToCustomerDto.convert(savedCustomer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
        log.debug("Deleted customer id: " + id);
    }
}
