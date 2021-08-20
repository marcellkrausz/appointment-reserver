package com.marcellkrausz.appointmentreserve.service.implementation;

import com.marcellkrausz.appointmentreserve.exception.AppointmentNotFoundException;
import com.marcellkrausz.appointmentreserve.model.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserve.converter.AppointmentDtoToAppointment;
import com.marcellkrausz.appointmentreserve.converter.AppointmentToAppointmentDto;
import com.marcellkrausz.appointmentreserve.model.Appointment;
import com.marcellkrausz.appointmentreserve.repository.AppointmentRepository;
import com.marcellkrausz.appointmentreserve.service.AppointmentService;
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
