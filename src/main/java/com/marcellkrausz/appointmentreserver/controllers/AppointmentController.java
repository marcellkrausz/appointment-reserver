package com.marcellkrausz.appointmentreserver.controllers;

import com.marcellkrausz.appointmentreserver.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserver.models.Appointment;
import com.marcellkrausz.appointmentreserver.services.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments")
    public Set<Appointment> getAll() {
        return appointmentService.getAllAppointment();
    }

    @GetMapping("/appointments/{id}")
    public Appointment getById(@PathVariable("id") Long id) {
        return appointmentService.getAppointmentById(id);
    }
    
    @PostMapping("/appointments")
    public void save(@RequestBody AppointmentDto appointmentDto) {
        appointmentService.saveAppointment(appointmentDto);
    }

    @PutMapping("/appointments/{id}")
    public void update(@RequestBody AppointmentDto appointmentDto, @PathVariable("id") Long id) {
        appointmentDto.setId(id);
        appointmentService.saveAppointment(appointmentDto);
    }

    @DeleteMapping("/appointments")
    public void delete(@PathVariable("id") Long id) {
        appointmentService.deleteAppointmentById(id);
    }
}
