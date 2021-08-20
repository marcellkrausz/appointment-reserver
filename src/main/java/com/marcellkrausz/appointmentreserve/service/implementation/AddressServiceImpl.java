package com.marcellkrausz.appointmentreserve.service.implementation;

import com.marcellkrausz.appointmentreserve.exception.AddressNotFoundException;
import com.marcellkrausz.appointmentreserve.model.dto.AddressDto;
import com.marcellkrausz.appointmentreserve.converter.AddressDtoToAddress;
import com.marcellkrausz.appointmentreserve.converter.AddressToAddressDto;
import com.marcellkrausz.appointmentreserve.model.Address;
import com.marcellkrausz.appointmentreserve.repository.AddressRepository;
import com.marcellkrausz.appointmentreserve.service.AddressService;
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
    public Set<AddressDto> getAllAddress() {
        Set<Address> addresses = new HashSet<>();
        addressRepository.findAll().iterator().forEachRemaining(addresses::add);
        if (addresses.isEmpty()) {
            throw new AddressNotFoundException("Addresses not found in database.");
        }
        return addressToAddressDto.convertSet(addresses);
    }

    @Override
    public AddressDto getAddressById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isEmpty()) {
            throw new AddressNotFoundException("Address not found.");
        }
        return addressToAddressDto.convert(addressOptional.get());
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
        getAddressById(id);

        addressRepository.deleteById(id);
        log.debug("Deleted address id: " + id);
    }
}
