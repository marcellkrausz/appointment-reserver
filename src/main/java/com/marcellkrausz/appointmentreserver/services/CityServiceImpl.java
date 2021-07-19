package com.marcellkrausz.appointmentreserver.services;

import com.marcellkrausz.appointmentreserver.dto.CityDto;
import com.marcellkrausz.appointmentreserver.converters.CityDtoToCity;
import com.marcellkrausz.appointmentreserver.converters.CityToCityDto;
import com.marcellkrausz.appointmentreserver.models.City;
import com.marcellkrausz.appointmentreserver.repositories.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityDtoToCity cityDtoToCity;
    private final CityToCityDto cityToCityDto;

    public CityServiceImpl(CityRepository cityRepository, CityDtoToCity cityDtoToCity, CityToCityDto cityToCityDto) {
        this.cityRepository = cityRepository;
        this.cityDtoToCity = cityDtoToCity;
        this.cityToCityDto = cityToCityDto;
    }

    @Override
    public Set<City> getAllCities() {
        Set<City> cities = new HashSet<>();
        cityRepository.findAll().iterator().forEachRemaining(cities::add);
        return cities;
    }

    @Override
    public City getCityById(Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);

        if (cityOptional.isEmpty()) {
            throw new RuntimeException("City not found in database");
        }
        return cityOptional.get();
    }

    @Override
    public CityDto saveCity(CityDto cityDto) {
        City detachedCity = cityDtoToCity.convert(cityDto);

        City savedCity = cityRepository.save(detachedCity);
        log.debug("Saved city id: " + savedCity.getId());
        return cityToCityDto.convert(savedCity);
    }

    @Override
    public void deleteCityById(Long id) {
        cityRepository.deleteById(id);
        log.debug("Deleted city id: " + id);
    }
}
