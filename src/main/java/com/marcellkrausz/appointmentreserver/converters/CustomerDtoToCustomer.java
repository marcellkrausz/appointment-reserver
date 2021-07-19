package com.marcellkrausz.appointmentreserver.converters;

import com.marcellkrausz.appointmentreserver.dto.CustomerDto;
import com.marcellkrausz.appointmentreserver.models.Address;
import com.marcellkrausz.appointmentreserver.models.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToCustomer implements Converter<CustomerDto, Customer> {

    @Override
    public Customer convert(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }
        final Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setEmail(customerDto.getEmail());

        if (customerDto.getAddressId() != null) {
            Address address = new Address();
            address.setId(customerDto.getAddressId());
        }
        return customer;
    }
}
