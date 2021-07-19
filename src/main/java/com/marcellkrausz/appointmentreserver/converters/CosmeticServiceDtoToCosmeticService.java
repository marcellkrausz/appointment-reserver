package com.marcellkrausz.appointmentreserver.converters;

import com.marcellkrausz.appointmentreserver.dto.CosmeticServiceDto;
import com.marcellkrausz.appointmentreserver.models.CosmeticService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CosmeticServiceDtoToCosmeticService implements Converter<CosmeticServiceDto, CosmeticService> {

    @Override
    public CosmeticService convert(CosmeticServiceDto cosmeticServiceDto) {
        if (cosmeticServiceDto == null) {
            return null;
        }
        final CosmeticService cosmeticService = new CosmeticService();
        cosmeticService.setId(cosmeticServiceDto.getId());
        cosmeticService.setName(cosmeticService.getName());
        cosmeticService.setPrice(cosmeticService.getPrice());
        cosmeticService.setMinutes(cosmeticService.getMinutes());

        return cosmeticService;
    }
}
