package com.marcellkrausz.appointmentreserve.controller;

import com.marcellkrausz.appointmentreserve.converter.StringToLong;
import com.marcellkrausz.appointmentreserve.exception.AddressNotFoundException;
import com.marcellkrausz.appointmentreserve.model.dto.AddressDto;
import com.marcellkrausz.appointmentreserve.service.AddressService;
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
    public AddressDto getById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new AddressNotFoundException("Must enter a valid number.");
        }
        return addressService.getAddressById(Long.valueOf(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDto save(@Valid @RequestBody AddressDto addressDto) {
        return addressService.saveAddress(addressDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody AddressDto addressDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new AddressNotFoundException("Must enter a valid number.");
        }
        addressDto.setId(Long.valueOf(id));
        addressService.saveAddress(addressDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new AddressNotFoundException("Must enter a valid number.");
        }
        addressService.deleteAddressById(Long.valueOf(id));
    }
}
