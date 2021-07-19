package com.marcellkrausz.appointmentreserver.converters;

import com.marcellkrausz.appointmentreserver.dto.CityDto;
import com.marcellkrausz.appointmentreserver.models.City;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CityDtoToCity implements Converter<CityDto, City> {

    @Override
    public City convert(CityDto cityDto) {
        if (cityDto == null) {
            return null;
        }

        final City city = new City();
        city.setId(cityDto.getId());
        city.setName(cityDto.getName());
        city.setPostalCode(city.getPostalCode());

        return city;
    }
}
