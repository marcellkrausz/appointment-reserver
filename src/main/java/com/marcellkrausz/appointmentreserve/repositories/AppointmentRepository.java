package com.marcellkrausz.appointmentreserve.repositories;

import com.marcellkrausz.appointmentreserve.models.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    @Query("from Appointment a where a.customer.firstName = ?1 and a.customer.lastName = ?2")
    Set<Appointment> getAllAppointmentsByCustomerName(String firstName, String lastName);
}
