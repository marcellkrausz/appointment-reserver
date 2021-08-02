package com.marcellkrausz.appointmentreserve.converters;

import com.marcellkrausz.appointmentreserve.models.dto.CityDto;
import com.marcellkrausz.appointmentreserve.models.City;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CityDtoToCity implements Converter<CityDto, City> {

    @Synchronized
    @Nullable
    @Override
    public City convert(CityDto cityDto) {
        if (cityDto == null) {
            return null;
        }

        final City city = new City();
        city.setId(cityDto.getId());
        city.setName(cityDto.getName());
        city.setPostalCode(cityDto.getPostalCode());

        return city;
    }
}
