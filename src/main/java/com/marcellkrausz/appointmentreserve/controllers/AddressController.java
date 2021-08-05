package com.marcellkrausz.appointmentreserve.controllers;

import com.marcellkrausz.appointmentreserve.models.dto.AddressDto;
import com.marcellkrausz.appointmentreserve.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping()
    public Set<AddressDto> getAll() {
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    public AddressDto getById(@PathVariable("id") Long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@Valid @RequestBody AddressDto addressDto) {
        addressService.saveAddress(addressDto);
        return addressDto.getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody AddressDto addressDto, @PathVariable("id") Long id) {
        addressDto.setId(id);
        addressService.saveAddress(addressDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        addressService.deleteAddressById(id);
    }
}
