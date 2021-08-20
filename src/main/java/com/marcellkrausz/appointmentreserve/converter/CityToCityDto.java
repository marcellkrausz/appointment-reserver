package com.marcellkrausz.appointmentreserve.converter;

import com.marcellkrausz.appointmentreserve.model.dto.CityDto;
import com.marcellkrausz.appointmentreserve.model.City;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CityToCityDto implements Converter<City, CityDto> {

    @Synchronized
    @Nullable
    @Override
    public CityDto convert(City city) {
        if (city == null) {
            return null;
        }

        final CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setName(city.getName());
        cityDto.setPostalCode(city.getPostalCode());

        return cityDto;
    }

    public Set<CityDto> convertSet(Set<City> cities) {
        Set<CityDto>CityDtos = new HashSet<>();
        for (City city : cities) {
            CityDtos.add(convert(city));
        }
        return CityDtos;
    }
}
