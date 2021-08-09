package com.marcellkrausz.appointmentreserve.controllers;

import com.marcellkrausz.appointmentreserve.converters.StringToLong;
import com.marcellkrausz.appointmentreserve.exception.BeautyCareNotFoundException;
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
    public BeautyCare getById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new BeautyCareNotFoundException("Must enter a valid number.");
        }
        return beautyCareService.getBeautyCareById(StringToLong.convert(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@Valid @RequestBody BeautyCareDto beautyCareDto) {
        beautyCareService.saveBeautyCare(beautyCareDto);
        return beautyCareDto.getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody BeautyCareDto beautyCareDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new BeautyCareNotFoundException("Must enter a valid number.");
        }
        beautyCareDto.setId(StringToLong.convert(id));
        beautyCareService.saveBeautyCare(beautyCareDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new BeautyCareNotFoundException("Must enter a valid number.");
        }
        beautyCareService.deleteBeautyCareById(StringToLong.convert(id));
    }
}
