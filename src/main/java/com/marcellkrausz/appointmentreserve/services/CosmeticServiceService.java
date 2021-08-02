package com.marcellkrausz.appointmentreserve.services;



import com.marcellkrausz.appointmentreserve.models.dto.CosmeticServiceDto;
import com.marcellkrausz.appointmentreserve.models.CosmeticService;

import java.util.Set;

public interface CosmeticServiceService {

    Set<CosmeticService> getAllCosmeticService();

    CosmeticService getCosmeticServiceById(Long id);

    CosmeticServiceDto saveCosmeticService(CosmeticServiceDto cosmeticServiceDto);

    void deleteCosmeticServiceById(Long id);
}
