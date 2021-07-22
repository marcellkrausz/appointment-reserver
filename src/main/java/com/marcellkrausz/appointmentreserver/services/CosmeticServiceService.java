package com.marcellkrausz.appointmentreserver.services;



import com.marcellkrausz.appointmentreserver.models.dto.CosmeticServiceDto;
import com.marcellkrausz.appointmentreserver.models.CosmeticService;

import java.util.Set;

public interface CosmeticServiceService {

    Set<CosmeticService> getAllCosmeticService();

    CosmeticService getCosmeticServiceById(Long id);

    CosmeticServiceDto saveCosmeticService(CosmeticServiceDto cosmeticServiceDto);

    void deleteCosmeticServiceById(Long id);
}
