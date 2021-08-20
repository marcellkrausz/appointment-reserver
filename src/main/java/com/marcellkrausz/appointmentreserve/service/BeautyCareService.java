package com.marcellkrausz.appointmentreserve.service;

import com.marcellkrausz.appointmentreserve.model.dto.BeautyCareDto;

import java.util.Set;

public interface BeautyCareService {

    Set<BeautyCareDto> getAllBeautyCare();

    BeautyCareDto getBeautyCareById(Long id);

    BeautyCareDto saveBeautyCare(BeautyCareDto beautyCareDto);

    void deleteBeautyCareById(Long id);
}
