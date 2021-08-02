package com.marcellkrausz.appointmentreserve.converters;

import com.marcellkrausz.appointmentreserve.models.dto.CityDto;
import com.marcellkrausz.appointmentreserve.models.City;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

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
}
