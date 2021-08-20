package com.marcellkrausz.appointmentreserve.service;

import com.marcellkrausz.appointmentreserve.model.dto.CityDto;


import java.util.Set;

public interface CityService {

    Set<CityDto> getAllCities();

    CityDto getCityById(Long id);

    CityDto saveCity(CityDto cityDto);

    void deleteCityById(Long id);
}
