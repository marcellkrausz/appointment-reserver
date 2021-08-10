package com.marcellkrausz.appointmentreserve.converters;

import com.marcellkrausz.appointmentreserve.models.Address;
import com.marcellkrausz.appointmentreserve.models.dto.AddressDto;
import com.marcellkrausz.appointmentreserve.models.dto.BeautyCareDto;
import com.marcellkrausz.appointmentreserve.models.BeautyCare;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BeautyCareToBeautyCareDto implements Converter<BeautyCare, BeautyCareDto> {

    @Synchronized
    @Nullable
    @Override
    public BeautyCareDto convert(BeautyCare beautyCare) {
        if (beautyCare == null) {
            return null;
        }

        final BeautyCareDto beautyCareDto = new BeautyCareDto();
        beautyCareDto.setId(beautyCare.getId());
        beautyCareDto.setName(beautyCare.getName());
        beautyCareDto.setPrice(beautyCare.getPrice());
        beautyCareDto.setMinutes(beautyCare.getMinutes());

        return beautyCareDto;
    }

    public Set<BeautyCareDto> convertSet(Set<BeautyCare> beautyCares) {
        Set<BeautyCareDto> beautyCareDtos = new HashSet<>();
        for (BeautyCare beautyCare : beautyCares) {
           beautyCareDtos.add(convert(beautyCare));
        }
        return beautyCareDtos;
    }
}
