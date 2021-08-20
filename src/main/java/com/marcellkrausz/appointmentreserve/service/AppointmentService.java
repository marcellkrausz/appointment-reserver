package com.marcellkrausz.appointmentreserve.service;

import com.marcellkrausz.appointmentreserve.model.dto.AppointmentDto;

import java.util.Set;

public interface AppointmentService {

    Set<AppointmentDto> getAllAppointment();

    AppointmentDto getAppointmentById(Long id);

    AppointmentDto saveAppointment(AppointmentDto appointmentDto);

    void deleteAppointmentById(Long id);

    Set<AppointmentDto> getAllAppointmentsByCustomerName(String firstName, String lastName);
}
