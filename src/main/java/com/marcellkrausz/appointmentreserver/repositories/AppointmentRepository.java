package com.marcellkrausz.appointmentreserver.repositories;

import com.marcellkrausz.appointmentreserver.models.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
