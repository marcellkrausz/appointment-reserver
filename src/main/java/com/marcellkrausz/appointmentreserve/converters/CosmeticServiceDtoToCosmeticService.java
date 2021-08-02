package com.marcellkrausz.appointmentreserve.converters;

import com.marcellkrausz.appointmentreserve.models.dto.CosmeticServiceDto;
import com.marcellkrausz.appointmentreserve.models.CosmeticService;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CosmeticServiceDtoToCosmeticService implements Converter<CosmeticServiceDto, CosmeticService> {

    @Synchronized
    @Nullable
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
