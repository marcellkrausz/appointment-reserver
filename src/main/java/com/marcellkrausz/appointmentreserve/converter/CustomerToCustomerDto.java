package com.marcellkrausz.appointmentreserve.converter;

import com.marcellkrausz.appointmentreserve.model.dto.CustomerDto;
import com.marcellkrausz.appointmentreserve.model.Customer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomerToCustomerDto implements Converter<Customer, CustomerDto> {

    @Synchronized
    @Nullable
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

    public Set<CustomerDto> convertSet(Set<Customer> customers) {
        Set<CustomerDto> customerDtos = new HashSet<>();
        for (Customer customer : customers) {
            customerDtos.add(convert(customer));
        }
        return customerDtos;
    }
}
