package com.marcellkrausz.appointmentreserver.converters;

import com.marcellkrausz.appointmentreserver.dto.CosmeticServiceDto;
import com.marcellkrausz.appointmentreserver.models.CosmeticService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CosmeticServiceToCosmeticServiceDto implements Converter<CosmeticService, CosmeticServiceDto> {

    @Override
    public CosmeticServiceDto convert(CosmeticService cosmeticService) {
        if (cosmeticService == null) {
            return null;
        }

        final CosmeticServiceDto cosmeticServiceDto = new CosmeticServiceDto();
        cosmeticServiceDto.setId(cosmeticService.getId());
        cosmeticServiceDto.setName(cosmeticService.getName());
        cosmeticServiceDto.setPrice(cosmeticService.getPrice());
        cosmeticServiceDto.setMinutes(cosmeticService.getMinutes());

        return cosmeticServiceDto;
    }
}
