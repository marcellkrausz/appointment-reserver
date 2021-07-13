package com.marcellkrausz.appointmentreserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class CosmeticService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;

    @ManyToMany(mappedBy = "cosmeticServices")
    private Set<Appointment> serviceAppointments = new HashSet<>();
}
