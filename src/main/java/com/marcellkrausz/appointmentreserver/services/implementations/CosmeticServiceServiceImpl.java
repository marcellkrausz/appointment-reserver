package com.marcellkrausz.appointmentreserver.services.implementations;

import com.marcellkrausz.appointmentreserver.models.dto.CosmeticServiceDto;
import com.marcellkrausz.appointmentreserver.converters.CosmeticServiceDtoToCosmeticService;
import com.marcellkrausz.appointmentreserver.converters.CosmeticServiceToCosmeticServiceDto;
import com.marcellkrausz.appointmentreserver.models.CosmeticService;
import com.marcellkrausz.appointmentreserver.repositories.CosmeticServiceRepository;
import com.marcellkrausz.appointmentreserver.services.CosmeticServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CosmeticServiceServiceImpl implements CosmeticServiceService {

    private final CosmeticServiceRepository cosmeticServiceRepository;
    private final CosmeticServiceDtoToCosmeticService cosmeticServiceDtoToCosmeticService;
    private final CosmeticServiceToCosmeticServiceDto cosmeticServiceToCosmeticServiceDto;

    public CosmeticServiceServiceImpl(CosmeticServiceRepository cosmeticServiceRepository, CosmeticServiceDtoToCosmeticService cosmeticServiceDtoToCosmeticService, CosmeticServiceToCosmeticServiceDto cosmeticServiceToCosmeticServiceDto) {
        this.cosmeticServiceRepository = cosmeticServiceRepository;
        this.cosmeticServiceDtoToCosmeticService = cosmeticServiceDtoToCosmeticService;
        this.cosmeticServiceToCosmeticServiceDto = cosmeticServiceToCosmeticServiceDto;
    }

    @Override
    public Set<CosmeticService> getAllCosmeticService() {
        Set<CosmeticService> cosmeticServices = new HashSet<>();
        cosmeticServiceRepository.findAll().iterator().forEachRemaining(cosmeticServices::add);
        return cosmeticServices;
    }

    @Override
    public CosmeticService getCosmeticServiceById(Long id) {
        Optional<CosmeticService> cosmeticServiceOptional = cosmeticServiceRepository.findById(id);
        if (cosmeticServiceOptional.isEmpty()) {
            throw new RuntimeException("Cosmetic Service not found");
        }
        return cosmeticServiceOptional.get();
    }

    @Override
    public CosmeticServiceDto saveCosmeticService(CosmeticServiceDto cosmeticServiceDto) {
        CosmeticService detachedCosmeticService = cosmeticServiceDtoToCosmeticService.convert(cosmeticServiceDto);

        CosmeticService savedCosmeticService = cosmeticServiceRepository.save(detachedCosmeticService);
        log.debug("Saved cosmetic service id: " + savedCosmeticService.getId());
        return cosmeticServiceToCosmeticServiceDto.convert(savedCosmeticService);
    }

    @Override
    public void deleteCosmeticServiceById(Long id) {
        cosmeticServiceRepository.deleteById(id);
        log.debug("Deleted cosmetic service id: " + id);
    }
}
