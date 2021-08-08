package com.marcellkrausz.appointmentreserve.services;

import com.marcellkrausz.appointmentreserve.models.dto.CustomerDto;
import com.marcellkrausz.appointmentreserve.models.Customer;

import java.util.Set;

public interface CustomerService {

    Set<Customer> getAllCustomer();

    Customer getCustomerById(Long id);

    CustomerDto saveCustomer(CustomerDto customerDto);

    void deleteCustomerById(Long id);

}
