package com.marcellkrausz.appointmentreserve.service.implementation;

import com.marcellkrausz.appointmentreserve.exception.CustomerNotFoundException;
import com.marcellkrausz.appointmentreserve.model.dto.CustomerDto;
import com.marcellkrausz.appointmentreserve.converter.CustomerDtoToCustomer;
import com.marcellkrausz.appointmentreserve.converter.CustomerToCustomerDto;
import com.marcellkrausz.appointmentreserve.model.Customer;
import com.marcellkrausz.appointmentreserve.repository.CustomerRepository;
import com.marcellkrausz.appointmentreserve.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Set<CustomerDto> getAllCustomer() {
        Set<Customer> customers = new HashSet<>();
        customerRepository.findAll().iterator().forEachRemaining(customers::add);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("Customers not found in database.");
        }
        return customerToCustomerDto.convertSet(customers);
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return customerToCustomerDto.convert(customerOptional.get());
    }

    @Transactional
    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer detachedCustomer = customerDtoToCustomer.convert(customerDto);

        Customer savedCustomer = customerRepository.save(detachedCustomer);
        log.debug("Saved Customer id: " + savedCustomer.getId());
        return customerToCustomerDto.convert(savedCustomer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        getCustomerById(id);

        customerRepository.deleteById(id);
        log.debug("Deleted customer id: " + id);
    }
}
