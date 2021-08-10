package com.marcellkrausz.appointmentreserve.converters;

import com.marcellkrausz.appointmentreserve.models.dto.AddressDto;
import com.marcellkrausz.appointmentreserve.models.Address;
import com.marcellkrausz.appointmentreserve.models.City;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoToAddress implements Converter<AddressDto, Address> {

    @Synchronized
    @Nullable
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
            city.setId(addressDto.getCityId());
            city.getAddresses().add(address);
            address.setCity(city);
        }
        return address;
    }
}
