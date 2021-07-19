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
@JsonIgnoreProperties("customerAppointments")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    @OneToOne
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Appointment> customerAppointments = new HashSet<>();

    public void addAppointment(Appointment appointment) {
        this.customerAppointments.add(appointment);
    }
}
