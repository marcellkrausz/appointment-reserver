package com.marcellkrausz.appointmentreserve.controller;

import com.marcellkrausz.appointmentreserve.converter.StringToLong;
import com.marcellkrausz.appointmentreserve.exception.AppointmentNotFoundException;
import com.marcellkrausz.appointmentreserve.model.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserve.service.AppointmentService;
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
    public AppointmentDto getById(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new AppointmentNotFoundException("Must enter a valid number.");
        }
        return appointmentService.getAppointmentById(Long.valueOf(id));
    }

    @GetMapping("/customer")
    public Set<AppointmentDto> getAllByCustomerName(@RequestParam String firstname, @RequestParam String lastName) {
       return appointmentService.getAllAppointmentsByCustomerName(firstname, lastName);
    }
    
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDto save(@Valid @RequestBody AppointmentDto appointmentDto) {
        return appointmentService.saveAppointment(appointmentDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody AppointmentDto appointmentDto, @PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new AppointmentNotFoundException("Must enter a valid number.");
        }
        appointmentDto.setId(Long.valueOf(id));
        appointmentService.saveAppointment(appointmentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        if (StringToLong.convert(id) == null) {
            throw new AppointmentNotFoundException("Must enter a valid number.");
        }
        appointmentService.deleteAppointmentById(Long.valueOf(id));
    }
}
