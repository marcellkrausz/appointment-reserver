package com.marcellkrausz.appointmentreserver.repository;

import com.marcellkrausz.appointmentreserver.model.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
