package com.marcellkrausz.appointmentreserver.converters;

import com.marcellkrausz.appointmentreserver.dto.CustomerDto;
import com.marcellkrausz.appointmentreserver.models.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerDto implements Converter<Customer, CustomerDto> {

    @Override
    public CustomerDto convert(Customer customer) {
        if (customer == null) {
            return null;
        }

        final CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhoneNumber(customer.getPhoneNumber());

        if (customer.getAddress() != null) {
            customerDto.setAddressId(customer.getAddress().getId());
        }
        return customerDto;
    }
}
