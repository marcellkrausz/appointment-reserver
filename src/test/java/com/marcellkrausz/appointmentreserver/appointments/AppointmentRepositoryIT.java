package com.marcellkrausz.appointmentreserver.appointments;

import static org.assertj.core.api.Assertions.assertThat;
import com.marcellkrausz.appointmentreserver.models.Appointment;
import com.marcellkrausz.appointmentreserver.repositories.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@DataJpaTest
public class AppointmentRepositoryIT {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Test
    void testPersist() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        appointment.setAppointmentDateStart(LocalDateTime.of(2021,07,18,15,00,00));
        appointment.setAppointmentDateStart(LocalDateTime.of(2021,07,18,14,00,00));

        appointmentRepository.save(appointment);
        Set<Appointment> appointments = new HashSet<>();
        appointmentRepository.findAll().iterator().forEachRemaining(appointments::add);

        assertThat(appointments).extracting(Appointment::getAppointmentDateStart).containsExactly(LocalDateTime.of(2021,07,18,15,00,00));
    }
}
