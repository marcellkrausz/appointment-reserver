package com.marcellkrausz.appointmentreserve.services;

import com.marcellkrausz.appointmentreserve.models.dto.CityDto;
import com.marcellkrausz.appointmentreserve.models.City;

import java.util.Set;

public interface CityService {

    Set<City> getAllCities();

    City getCityById(Long id);

    CityDto saveCity(CityDto cityDto);

    void deleteCityById(Long id);
}
