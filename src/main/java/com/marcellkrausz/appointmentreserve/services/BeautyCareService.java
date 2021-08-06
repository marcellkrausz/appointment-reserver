package com.marcellkrausz.appointmentreserve.services;



import com.marcellkrausz.appointmentreserve.models.BeautyCare;
import com.marcellkrausz.appointmentreserve.models.dto.BeautyCareDto;

import java.util.Set;

public interface BeautyCareService {

    Set<BeautyCare> getAllBeautyCare();

    BeautyCare getBeautyCareById(Long id);

    BeautyCareDto saveBeautyCare(BeautyCareDto beautyCareDto);

    void deleteBeautyCareById(Long id);
}
