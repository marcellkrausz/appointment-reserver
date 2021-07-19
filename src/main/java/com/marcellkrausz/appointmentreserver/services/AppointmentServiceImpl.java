package com.marcellkrausz.appointmentreserver.services;

import com.marcellkrausz.appointmentreserver.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserver.converters.AppointmentDtoToAppointment;
import com.marcellkrausz.appointmentreserver.converters.AppointmentToAppointmentDto;
import com.marcellkrausz.appointmentreserver.models.Appointment;
import com.marcellkrausz.appointmentreserver.repositories.AppointmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public Set<Appointment> getAllAppointment() {
        Set<Appointment> appointments = new HashSet<>();
        appointmentRepository.findAll().iterator().forEachRemaining(appointments::add);
        return appointments;
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isEmpty()) {
            throw new RuntimeException("Appointment not found");
        }
        return appointmentOptional.get();
    }

    @Override
    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
        Appointment detachedAppointment = appointmentDtoToAppointment.convert(appointmentDto);

        Appointment savedAppointment = appointmentRepository.save(detachedAppointment);
        log.debug("Saved appointment id: " + savedAppointment.getId());
        return appointmentToAppointmentDto.convert(savedAppointment);
    }

    @Override
    public void deleteAppointmentById(Long id) {
        appointmentRepository.deleteById(id);
        log.debug("Deleted appointment id: " + id);
    }
}
