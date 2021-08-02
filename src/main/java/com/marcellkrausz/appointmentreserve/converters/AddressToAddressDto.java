package com.marcellkrausz.appointmentreserve.converters;

import com.marcellkrausz.appointmentreserve.models.dto.AddressDto;
import com.marcellkrausz.appointmentreserve.models.Address;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AddressToAddressDto implements Converter<Address, AddressDto> {

    @Synchronized
    @Nullable
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

    public Set<AddressDto> convertSet(Set<Address> addressesSet) {
        Set<AddressDto> addressDtos = new HashSet<>();
        for (Address address : addressesSet) {
            addressDtos.add(convert(address));
        }
        return addressDtos;
    }
}
