package com.marcellkrausz.appointmentreserver.services;

import com.marcellkrausz.appointmentreserver.models.dto.CityDto;
import com.marcellkrausz.appointmentreserver.models.City;

import java.util.Set;

public interface CityService {

    Set<City> getAllCities();

    City getCityById(Long id);

    CityDto saveCity(CityDto cityDto);

    void deleteCityById(Long id);
}
