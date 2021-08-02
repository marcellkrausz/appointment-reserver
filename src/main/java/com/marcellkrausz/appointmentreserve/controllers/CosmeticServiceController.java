package com.marcellkrausz.appointmentreserve.controllers;

import com.marcellkrausz.appointmentreserve.models.dto.CosmeticServiceDto;
import com.marcellkrausz.appointmentreserve.models.CosmeticService;
import com.marcellkrausz.appointmentreserve.services.CosmeticServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@Valid @RequestBody CosmeticServiceDto cosmeticServiceDto) {
        cosmeticServiceService.saveCosmeticService(cosmeticServiceDto);
        return cosmeticServiceDto.getId();
    }

    @PutMapping("/cosmeticservice/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody CosmeticServiceDto cosmeticServiceDto, @PathVariable("id") Long id) {
        cosmeticServiceDto.setId(id);
        cosmeticServiceService.saveCosmeticService(cosmeticServiceDto);
    }

    @DeleteMapping("/cosmeticservice/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        cosmeticServiceService.deleteCosmeticServiceById(id);
    }
}
