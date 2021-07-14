package com.marcellkrausz.appointmentreserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appointmentDateStart;
    private LocalDateTime appointmentDateEnd;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private CosmeticService cosmeticService;


}
