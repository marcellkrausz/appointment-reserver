package com.marcellkrausz.appointmentreserve.services;

import com.marcellkrausz.appointmentreserve.models.dto.CustomerDto;

import java.util.Set;

public interface CustomerService {

    Set<CustomerDto> getAllCustomer();

    CustomerDto getCustomerById(Long id);

    CustomerDto saveCustomer(CustomerDto customerDto);

    void deleteCustomerById(Long id);

}
