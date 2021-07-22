package com.marcellkrausz.appointmentreserver.services;

import com.marcellkrausz.appointmentreserver.models.dto.AddressDto;
import com.marcellkrausz.appointmentreserver.models.Address;

import java.util.Set;

public interface AddressService {

    Set<Address> getAllAddress();

    Address getAddressById(Long id);

    AddressDto saveAddress(AddressDto addressDto);

    void deleteAddressById(Long id);
}
