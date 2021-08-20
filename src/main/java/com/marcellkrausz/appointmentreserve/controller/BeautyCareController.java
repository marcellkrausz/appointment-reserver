package com.marcellkrausz.appointmentreserve.controller;

import com.marcellkrausz.appointmentreserve.converter.StringToLong;
import com.marcellkrausz.appointmentreserve.exception.BeautyCareNotFoundException;
import com.marcellkrausz.appointmentreserve.model.dto.BeautyCareDto;
import com.marcellkrausz.appointmentreserve.service.BeautyCareService;
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
    public Set<BeautyCareDto> getAll() {
        return beautyCareService.getAllBeautyCare();
    }

    @GetMapping("/{id}")
    public BeautyCareDto getById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new BeautyCareNotFoundException("Must enter a valid number.");
        }
        return beautyCareService.getBeautyCareById(Long.valueOf(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BeautyCareDto save(@Valid @RequestBody BeautyCareDto beautyCareDto) {
        return beautyCareService.saveBeautyCare(beautyCareDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody BeautyCareDto beautyCareDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new BeautyCareNotFoundException("Must enter a valid number.");
        }
        beautyCareDto.setId(Long.valueOf(id));
        beautyCareService.saveBeautyCare(beautyCareDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new BeautyCareNotFoundException("Must enter a valid number.");
        }
        beautyCareService.deleteBeautyCareById(Long.valueOf(id));
    }
}
