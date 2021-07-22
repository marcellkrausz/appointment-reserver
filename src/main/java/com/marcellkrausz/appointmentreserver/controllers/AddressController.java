package com.marcellkrausz.appointmentreserver.controllers;

import com.marcellkrausz.appointmentreserver.models.dto.AddressDto;
import com.marcellkrausz.appointmentreserver.models.Address;
import com.marcellkrausz.appointmentreserver.services.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/address")
    public Set<Address> getAll() {
        return addressService.getAllAddress();
    }

    @GetMapping("/address/{id}")
    public Address getById(@PathVariable("id") Long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping("/address")
    public void save(@RequestBody AddressDto addressDto) {
        addressService.saveAddress(addressDto);
    }

    @PutMapping("/address/{id}")
    public void update(@RequestBody AddressDto addressDto, @PathVariable("id") Long id) {
        addressDto.setId(id);
        addressService.saveAddress(addressDto);
    }

    @DeleteMapping("/address/{id}")
    public void delete(@PathVariable("id") Long id) {
        addressService.deleteAddressById(id);
    }
}
