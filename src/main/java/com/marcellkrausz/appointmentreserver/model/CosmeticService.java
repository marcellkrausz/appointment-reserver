package com.marcellkrausz.appointmentreserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private Integer price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cosmeticService")
    private Set<Appointment> serviceAppointments = new HashSet<>();
}
