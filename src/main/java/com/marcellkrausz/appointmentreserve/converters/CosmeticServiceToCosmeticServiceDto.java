package com.marcellkrausz.appointmentreserve.converters;

import com.marcellkrausz.appointmentreserve.models.dto.CosmeticServiceDto;
import com.marcellkrausz.appointmentreserve.models.CosmeticService;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CosmeticServiceToCosmeticServiceDto implements Converter<CosmeticService, CosmeticServiceDto> {

    @Synchronized
    @Nullable
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