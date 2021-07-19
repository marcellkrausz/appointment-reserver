package com.marcellkrausz.appointmentreserver.converters;

import com.marcellkrausz.appointmentreserver.dto.AddressDto;
import com.marcellkrausz.appointmentreserver.models.Address;
import com.marcellkrausz.appointmentreserver.models.City;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoToAddress implements Converter<AddressDto, Address> {

    @Override
    public Address convert(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }
        final Address address = new Address();
        address.setId(addressDto.getId());
        address.setStreet(addressDto.getStreet());
        address.setHouseNumber(addressDto.getHouseNumber());

        if (addressDto.getCityId() != null) {
            City city = new City();
            city.setId(addressDto.getId());
            address.setCity(city);
            city.addAddress(address);
        }
        return address;
    }
}
