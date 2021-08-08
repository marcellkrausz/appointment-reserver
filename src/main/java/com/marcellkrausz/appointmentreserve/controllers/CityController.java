package com.marcellkrausz.appointmentreserve.controllers;

import com.marcellkrausz.appointmentreserve.models.dto.CityDto;
import com.marcellkrausz.appointmentreserve.models.City;
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
    public Set<City> getAll() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public City getById(@PathVariable("id") Long id) {
        return cityService.getCityById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@RequestBody CityDto cityDto) {
        cityService.saveCity(cityDto);
        return cityDto.getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody CityDto cityDto, @PathVariable("id") Long id) {
        cityDto.setId(id);
        cityService.saveCity(cityDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        cityService.deleteCityById(id);
    }
}
