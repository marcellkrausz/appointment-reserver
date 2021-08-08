package com.marcellkrausz.appointmentreserve.controllers;

import com.marcellkrausz.appointmentreserve.models.BeautyCare;
import com.marcellkrausz.appointmentreserve.models.dto.BeautyCareDto;
import com.marcellkrausz.appointmentreserve.services.BeautyCareService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/beautycare")
public class BeautyCareController {

    private final BeautyCareService beautyCareService;

    public BeautyCareController(BeautyCareService beautyCareService) {
        this.beautyCareService = beautyCareService;
    }

    @GetMapping()
    public Set<BeautyCare> getAll() {
        return beautyCareService.getAllBeautyCare();
    }

    @GetMapping("/{id}")
    public BeautyCare getById(@PathVariable("id") Long id) {
        return beautyCareService.getBeautyCareById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@Valid @RequestBody BeautyCareDto beautyCareDto) {
        beautyCareService.saveBeautyCare(beautyCareDto);
        return beautyCareDto.getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody BeautyCareDto beautyCareDto, @PathVariable("id") Long id) {
        beautyCareDto.setId(id);
        beautyCareService.saveBeautyCare(beautyCareDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        beautyCareService.deleteBeautyCareById(id);
    }
}
