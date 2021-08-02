package com.marcellkrausz.appointmentreserve.controllers;

import com.marcellkrausz.appointmentreserve.models.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserve.models.Appointment;
import com.marcellkrausz.appointmentreserve.services.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping()
    public Set<AppointmentDto> getAll() {
        return appointmentService.getAllAppointment();
    }

    @GetMapping("/{id}")
    public AppointmentDto getById(@PathVariable("id") Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/customer")
    public Set<Appointment> getAllByCustomerName(@RequestParam String firstname, @RequestParam String lastName) {
       return appointmentService.getAllAppointmentsByCustomerName(firstname, lastName);
    }
    
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@Valid @RequestBody AppointmentDto appointmentDto) {
        appointmentService.saveAppointment(appointmentDto);
        return appointmentDto.getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody AppointmentDto appointmentDto, @PathVariable("id") Long id) {
        appointmentDto.setId(id);
        appointmentService.saveAppointment(appointmentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        appointmentService.deleteAppointmentById(id);
    }
}
