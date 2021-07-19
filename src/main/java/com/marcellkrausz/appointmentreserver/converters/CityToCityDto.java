package com.marcellkrausz.appointmentreserver.converters;

import com.marcellkrausz.appointmentreserver.dto.CityDto;
import com.marcellkrausz.appointmentreserver.models.City;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CityToCityDto implements Converter<City, CityDto> {

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
}
