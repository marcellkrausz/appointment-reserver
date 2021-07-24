package com.marcellkrausz.appointmentreserver.controllers;

import com.marcellkrausz.appointmentreserver.models.dto.CityDto;
import com.marcellkrausz.appointmentreserver.models.City;
import com.marcellkrausz.appointmentreserver.services.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/city")
    public Set<City> getAll() {
        return cityService.getAllCities();
    }

    @GetMapping("/city/{id}")
    public City getById(@PathVariable("id") Long id) {
        return cityService.getCityById(id);
    }

    @PostMapping("/city")
    public void save(@RequestBody CityDto cityDto) {
        cityService.saveCity(cityDto);
    }

    @PutMapping("/city/{id}")
    public void update(@RequestBody CityDto cityDto, @PathVariable("id") Long id) {
        cityDto.setId(id);
        cityService.saveCity(cityDto);
    }

    @DeleteMapping("/city/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        cityService.deleteCityById(id);
    }
}
