package com.marcellkrausz.appointmentreserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appointmentDateStart;
    private LocalDateTime appointmentStartEnd;
    private AppointmentStatus appointmentStatus = AppointmentStatus.AVAILABLE;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "appointment_cosmetic_service",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "cosmetic_service_id"))
    private Set<CosmeticService> cosmeticServices = new HashSet<>();


}
