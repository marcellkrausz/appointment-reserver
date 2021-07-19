package com.marcellkrausz.appointmentreserver.controllers;

import com.marcellkrausz.appointmentreserver.dto.CosmeticServiceDto;
import com.marcellkrausz.appointmentreserver.models.CosmeticService;
import com.marcellkrausz.appointmentreserver.services.CosmeticServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class CosmeticServiceController {

    private final CosmeticServiceService cosmeticServiceService;

    public CosmeticServiceController(CosmeticServiceService cosmeticServiceService) {
        this.cosmeticServiceService = cosmeticServiceService;
    }

    @GetMapping("/cosmeticservice")
    public Set<CosmeticService> getAll() {
        return cosmeticServiceService.getAllCosmeticService();
    }

    @GetMapping("/cosmeticservice/{id}")
    public CosmeticService getById(@PathVariable("id") Long id) {
        return cosmeticServiceService.getCosmeticServiceById(id);
    }

    @PostMapping("/cosmeticservice")
    public void save(@RequestBody CosmeticServiceDto cosmeticServiceDto) {
        cosmeticServiceService.saveCosmeticService(cosmeticServiceDto);
    }

    @PutMapping("/cosmeticservice/{id}")
    public void update(@RequestBody CosmeticServiceDto cosmeticServiceDto, @PathVariable("id") Long id) {
        cosmeticServiceDto.setId(id);
        cosmeticServiceService.saveCosmeticService(cosmeticServiceDto);
    }

    @DeleteMapping("/cosmeticservice")
    public void delete(@PathVariable("id") Long id) {
        cosmeticServiceService.deleteCosmeticServiceById(id);
    }
}
