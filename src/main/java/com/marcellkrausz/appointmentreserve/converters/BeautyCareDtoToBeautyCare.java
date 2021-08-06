package com.marcellkrausz.appointmentreserve.converters;

import com.marcellkrausz.appointmentreserve.models.BeautyCare;
import com.marcellkrausz.appointmentreserve.models.dto.BeautyCareDto;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BeautyCareDtoToBeautyCare implements Converter<BeautyCareDto, BeautyCare> {

    @Synchronized
    @Nullable
    @Override
    public BeautyCare convert(BeautyCareDto beautyCareDto) {
        if (beautyCareDto == null) {
            return null;
        }

        final BeautyCare beautyCare = new BeautyCare();
        beautyCare.setId(beautyCare.getId());
        beautyCare.setName(beautyCare.getName());
        beautyCare.setPrice(beautyCare.getPrice());
        beautyCare.setMinutes(beautyCare.getMinutes());

        return beautyCare;
    }
}
