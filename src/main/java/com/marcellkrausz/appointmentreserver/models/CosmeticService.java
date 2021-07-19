package com.marcellkrausz.appointmentreserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@JsonIgnoreProperties("serviceAppointments")
public class CosmeticService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private Integer minutes;

    @ManyToMany(mappedBy = "cosmeticServices")
    private Set<Appointment> serviceAppointments = new HashSet<>();
}
