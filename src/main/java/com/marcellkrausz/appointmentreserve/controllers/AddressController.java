package com.marcellkrausz.appointmentreserve.controllers;

import com.marcellkrausz.appointmentreserve.converters.StringToLong;
import com.marcellkrausz.appointmentreserve.exceptions.AddressNotFoundException;
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
    public AddressDto getById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new AddressNotFoundException("Must enter a valid number.");
        }
        return addressService.getAddressById(StringToLong.convert(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@Valid @RequestBody AddressDto addressDto) {
        addressService.saveAddress(addressDto);
        return addressDto.getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody AddressDto addressDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new AddressNotFoundException("Must enter a valid number.");
        }
        addressDto.setId(StringToLong.convert(id));
        addressService.saveAddress(addressDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new AddressNotFoundException("Must enter a valid number.");
        }
        addressService.deleteAddressById(StringToLong.convert(id));
    }
}
