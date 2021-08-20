package com.marcellkrausz.appointmentreserve.service;

import com.marcellkrausz.appointmentreserve.model.dto.AddressDto;

import java.util.Set;

public interface AddressService {

    Set<AddressDto> getAllAddress();

    AddressDto getAddressById(Long id);

    AddressDto saveAddress(AddressDto addressDto);

    void deleteAddressById(Long id);
}
