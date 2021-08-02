package com.marcellkrausz.appointmentreserve.services;

import com.marcellkrausz.appointmentreserve.models.dto.AddressDto;
import com.marcellkrausz.appointmentreserve.models.Address;

import java.util.Set;

public interface AddressService {

    Set<AddressDto> getAllAddress();

    AddressDto getAddressById(Long id);

    AddressDto saveAddress(AddressDto addressDto);

    void deleteAddressById(Long id);
}
