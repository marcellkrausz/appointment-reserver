package com.marcellkrausz.appointmentreserve.services;

import com.marcellkrausz.appointmentreserve.models.dto.CityDto;


import java.util.Set;

public interface CityService {

    Set<CityDto> getAllCities();

    CityDto getCityById(Long id);

    CityDto saveCity(CityDto cityDto);

    void deleteCityById(Long id);
}
