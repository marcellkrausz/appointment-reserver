package com.marcellkrausz.appointmentreserve.service.implementation;

import com.marcellkrausz.appointmentreserve.exception.CityNotFoundException;
import com.marcellkrausz.appointmentreserve.model.dto.CityDto;
import com.marcellkrausz.appointmentreserve.converter.CityDtoToCity;
import com.marcellkrausz.appointmentreserve.converter.CityToCityDto;
import com.marcellkrausz.appointmentreserve.model.City;
import com.marcellkrausz.appointmentreserve.repository.CityRepository;
import com.marcellkrausz.appointmentreserve.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Set<CityDto> getAllCities() {
        Set<City> cities = new HashSet<>();
        cityRepository.findAll().iterator().forEachRemaining(cities::add);
        if (cities.isEmpty()) {
            throw new CityNotFoundException("Cities not found in database.");
        }
        return cityToCityDto.convertSet(cities);
    }

    @Override
    public CityDto getCityById(Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isEmpty()) {
            throw new CityNotFoundException("City not found in database");
        }
        return cityToCityDto.convert(cityOptional.get());
    }

    @Transactional
    @Override
    public CityDto saveCity(CityDto cityDto) {
        City detachedCity = cityDtoToCity.convert(cityDto);
        City savedCity = cityRepository.save(detachedCity);
        log.debug("Saved city id: " + savedCity.getId());
        return cityToCityDto.convert(savedCity);
    }

    @Override
    public void deleteCityById(Long id) {
        getCityById(id);

        cityRepository.deleteById(id);
        log.debug("Deleted city id: " + id);
    }
}
