package com.marcellkrausz.appointmentreserve.services.implementations;

import com.marcellkrausz.appointmentreserve.exceptions.AppointmentNotFoundException;
import com.marcellkrausz.appointmentreserve.models.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserve.converters.AppointmentDtoToAppointment;
import com.marcellkrausz.appointmentreserve.converters.AppointmentToAppointmentDto;
import com.marcellkrausz.appointmentreserve.models.Appointment;
import com.marcellkrausz.appointmentreserve.repositories.AppointmentRepository;
import com.marcellkrausz.appointmentreserve.services.AppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentToAppointmentDto appointmentToAppointmentDto;
    private final AppointmentDtoToAppointment appointmentDtoToAppointment;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentToAppointmentDto appointmentToAppointmentDto, AppointmentDtoToAppointment appointmentDtoToAppointment) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentToAppointmentDto = appointmentToAppointmentDto;
        this.appointmentDtoToAppointment = appointmentDtoToAppointment;
    }

    @Override
    public Set<AppointmentDto> getAllAppointment() {
        Set<Appointment> appointments = new HashSet<>();
        appointmentRepository.findAll().iterator().forEachRemaining(appointments::add);
        if (appointments.isEmpty()) {
            throw new AppointmentNotFoundException("Appointments not found in database.");
        }
        return appointmentToAppointmentDto.convertSet(appointments);
    }

    @Override
    public AppointmentDto getAppointmentById(Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isEmpty()) {
            throw new AppointmentNotFoundException("Appointment not found");
        }
        return appointmentToAppointmentDto.convert(appointmentOptional.get());
    }

    @Transactional
    @Override
    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
        Appointment detachedAppointment = appointmentDtoToAppointment.convert(appointmentDto);

        Appointment savedAppointment = appointmentRepository.save(detachedAppointment);
        log.debug("Saved appointment id: " + savedAppointment.getId());
        return appointmentToAppointmentDto.convert(savedAppointment);
    }

    @Override
    public Set<AppointmentDto> getAllAppointmentsByCustomerName(String firstName, String lastName) {
        Set<Appointment> appointments = new HashSet<>();
        appointmentRepository.getAllAppointmentsByCustomerName(firstName, lastName).iterator().forEachRemaining(appointments::add);
        if (appointments.isEmpty()) {
            throw new AppointmentNotFoundException("Appointments not found");
        }
        Set<AppointmentDto> appointmentDtos = new HashSet<>();
        appointments.forEach(appointment -> appointmentDtos.add(appointmentToAppointmentDto.convert(appointment)));

        return appointmentDtos;
    }

    @Override
    public void deleteAppointmentById(Long id) {
        getAppointmentById(id);

        appointmentRepository.deleteById(id);
        log.debug("Deleted appointment id: " + id);
    }
}
