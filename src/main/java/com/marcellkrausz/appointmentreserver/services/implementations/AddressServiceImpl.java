package com.marcellkrausz.appointmentreserver.services.implementations;

import com.marcellkrausz.appointmentreserver.models.dto.AddressDto;
import com.marcellkrausz.appointmentreserver.converters.AddressDtoToAddress;
import com.marcellkrausz.appointmentreserver.converters.AddressToAddressDto;
import com.marcellkrausz.appointmentreserver.models.Address;
import com.marcellkrausz.appointmentreserver.repositories.AddressRepository;
import com.marcellkrausz.appointmentreserver.services.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressToAddressDto addressToAddressDto;
    private final AddressDtoToAddress addressDtoToAddress;

    public AddressServiceImpl(AddressRepository addressRepository, AddressToAddressDto addressToAddressDto, AddressDtoToAddress addressDtoToAddress) {
        this.addressRepository = addressRepository;
        this.addressToAddressDto = addressToAddressDto;
        this.addressDtoToAddress = addressDtoToAddress;
    }

    @Override
    public Set<Address> getAllAddress() {
        Set<Address> addresses = new HashSet<>();
        addressRepository.findAll().iterator().forEachRemaining(addresses::add);
        return addresses;
    }

    @Override
    public Address getAddressById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isEmpty()) {
            throw new RuntimeException("Address not found");
        }
        return addressOptional.get();
    }

    @Override
    public AddressDto saveAddress(AddressDto addressDto) {
        Address detachedAddress = addressDtoToAddress.convert(addressDto);
        Address savedAddress = addressRepository.save(detachedAddress);
        log.debug("Saved address Id: " + savedAddress.getId());
        return addressToAddressDto.convert(savedAddress);
    }

    @Override
    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
        log.debug("Deleted address id: " + id);
    }
}
