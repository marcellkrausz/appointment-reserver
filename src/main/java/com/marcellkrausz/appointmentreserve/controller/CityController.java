package com.marcellkrausz.appointmentreserve.controller;

import com.marcellkrausz.appointmentreserve.converter.StringToLong;
import com.marcellkrausz.appointmentreserve.exception.CityNotFoundException;
import com.marcellkrausz.appointmentreserve.model.dto.CityDto;
import com.marcellkrausz.appointmentreserve.service.CityService;
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
        return cityService.getCityById(Long.valueOf(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CityDto save(@RequestBody CityDto cityDto) {
        return cityService.saveCity(cityDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody CityDto cityDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CityNotFoundException("Must enter a valid number.");
        }
        cityDto.setId(Long.valueOf(id));
        cityService.saveCity(cityDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new CityNotFoundException("Must enter a valid number.");
        }
        cityService.deleteCityById(Long.valueOf(id));
    }
}
