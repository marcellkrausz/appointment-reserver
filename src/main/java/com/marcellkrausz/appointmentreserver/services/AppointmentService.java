package com.marcellkrausz.appointmentreserver.services;

import com.marcellkrausz.appointmentreserver.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserver.models.Appointment;

import java.util.Set;

public interface AppointmentService {

    Set<Appointment> getAllAppointment();

    Appointment getAppointmentById(Long id);

    AppointmentDto saveAppointment(AppointmentDto appointmentDto);

    void deleteAppointmentById(Long id);
}
