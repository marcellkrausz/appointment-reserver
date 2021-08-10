package com.marcellkrausz.appointmentreserve.services.implementations;

import com.marcellkrausz.appointmentreserve.exceptions.BeautyCareNotFoundException;
import com.marcellkrausz.appointmentreserve.models.BeautyCare;
import com.marcellkrausz.appointmentreserve.models.dto.BeautyCareDto;
import com.marcellkrausz.appointmentreserve.converters.BeautyCareDtoToBeautyCare;
import com.marcellkrausz.appointmentreserve.converters.BeautyCareToBeautyCareDto;
import com.marcellkrausz.appointmentreserve.repositories.BeautyCarteRepository;
import com.marcellkrausz.appointmentreserve.services.BeautyCareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class BeautyCareServiceImpl implements BeautyCareService {

    private final BeautyCarteRepository beautyCarteRepository;
    private final BeautyCareDtoToBeautyCare beautyCareDtoToBeautyCare;
    private final BeautyCareToBeautyCareDto beautyCareToBeautyCareDto;

    public BeautyCareServiceImpl(BeautyCarteRepository beautyCarteRepository, BeautyCareDtoToBeautyCare beautyCareDtoToBeautyCare, BeautyCareToBeautyCareDto beautyCareToBeautyCareDto) {
        this.beautyCarteRepository = beautyCarteRepository;
        this.beautyCareDtoToBeautyCare = beautyCareDtoToBeautyCare;
        this.beautyCareToBeautyCareDto = beautyCareToBeautyCareDto;
    }

    @Override
    public Set<BeautyCareDto> getAllBeautyCare() {
        Set<BeautyCare> beautyCares = new HashSet<>();
        beautyCarteRepository.findAll().iterator().forEachRemaining(beautyCares::add);
        if (beautyCares.isEmpty()) {
            throw new BeautyCareNotFoundException("Beauty cares not found in database.");
        }
        return beautyCareToBeautyCareDto.convertSet(beautyCares);
    }

    @Override
    public BeautyCareDto getBeautyCareById(Long id) {
        Optional<BeautyCare> cosmeticServiceOptional = beautyCarteRepository.findById(id);
        if (cosmeticServiceOptional.isEmpty()) {
            throw new BeautyCareNotFoundException("Beauty care not found");
        }
        return beautyCareToBeautyCareDto.convert(cosmeticServiceOptional.get());
    }

    @Transactional
    @Override
    public BeautyCareDto saveBeautyCare(BeautyCareDto beautyCareDto) {
        BeautyCare detachedBeautyCare = beautyCareDtoToBeautyCare.convert(beautyCareDto);

        BeautyCare savedBeautyCare = beautyCarteRepository.save(detachedBeautyCare);
        log.debug("Saved beauty care id: " + savedBeautyCare.getId());
        return beautyCareToBeautyCareDto.convert(savedBeautyCare);
    }

    @Override
    public void deleteBeautyCareById(Long id) {
        getBeautyCareById(id);

        beautyCarteRepository.deleteById(id);
        log.debug("Deleted beauty care id: " + id);
    }
}
