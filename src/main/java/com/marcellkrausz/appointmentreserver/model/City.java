package com.marcellkrausz.appointmentreserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int postalCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<Address> addresses = new HashSet<>();
}
