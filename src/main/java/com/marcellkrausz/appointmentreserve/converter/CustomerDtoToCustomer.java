package com.marcellkrausz.appointmentreserve.converter;

import com.marcellkrausz.appointmentreserve.model.dto.CustomerDto;
import com.marcellkrausz.appointmentreserve.model.Address;
import com.marcellkrausz.appointmentreserve.model.Customer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToCustomer implements Converter<CustomerDto, Customer> {

    @Synchronized
    @Nullable
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
            customer.setAddress(address);
        }
        return customer;
    }
}
