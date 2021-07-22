package com.marcellkrausz.appointmentreserver.services;

import com.marcellkrausz.appointmentreserver.models.dto.CustomerDto;
import com.marcellkrausz.appointmentreserver.models.Customer;

import java.util.Set;

public interface CustomerService {

    Set<Customer> getAllCustomer();

    Customer getCustomerById(Long id);

    CustomerDto saveCustomer(CustomerDto customerDto);

    void deleteCustomerById(Long id);
}
