package com.marcellkrausz.appointmentreserve.services;

import com.marcellkrausz.appointmentreserve.models.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserve.models.Appointment;

import java.util.List;
import java.util.Set;

public interface AppointmentService {

    Set<AppointmentDto> getAllAppointment();

    AppointmentDto getAppointmentById(Long id);

    AppointmentDto saveAppointment(AppointmentDto appointmentDto);

    void deleteAppointmentById(Long id);

    Set<Appointment> getAllAppointmentsByCustomerName(String firstName, String lastName);
}
