package com.marcellkrausz.appointmentreserve.controllers;

import com.marcellkrausz.appointmentreserve.converters.StringToLong;
import com.marcellkrausz.appointmentreserve.exceptions.CityNotFoundException;
import com.marcellkrausz.appointmentreserve.models.dto.CityDto;
import com.marcellkrausz.appointmentreserve.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping()
    public Set<CityDto> getAll() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public CityDto getById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CityNotFoundException("Must enter a valid number.");
        }
        return cityService.getCityById(StringToLong.convert(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody CityDto cityDto) {
        cityService.saveCity(cityDto);
        return cityDto.getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody CityDto cityDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CityNotFoundException("Must enter a valid number.");
        }
        cityDto.setId(StringToLong.convert(id));
        cityService.saveCity(cityDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CityNotFoundException("Must enter a valid number.");
        }
        cityService.deleteCityById(StringToLong.convert(id));
    }
}
