package com.marcellkrausz.appointmentreserve.service;

import com.marcellkrausz.appointmentreserve.model.dto.CustomerDto;

import java.util.Set;

public interface CustomerService {

    Set<CustomerDto> getAllCustomer();

    CustomerDto getCustomerById(Long id);

    CustomerDto saveCustomer(CustomerDto customerDto);

    void deleteCustomerById(Long id);

}
