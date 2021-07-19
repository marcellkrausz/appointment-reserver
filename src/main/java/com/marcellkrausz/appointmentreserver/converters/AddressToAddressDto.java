package com.marcellkrausz.appointmentreserver.converters;

import com.marcellkrausz.appointmentreserver.dto.AddressDto;
import com.marcellkrausz.appointmentreserver.models.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressDto implements Converter<Address, AddressDto> {

    @Override
    public AddressDto convert(Address address) {
        if (address == null) {
            return null;
        }

        final AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setStreet(address.getStreet());
        addressDto.setHouseNumber(address.getHouseNumber());

        if (address.getCity() != null) {
            addressDto.setCityId(address.getCity().getId());
        }
        return addressDto;
    }
}
