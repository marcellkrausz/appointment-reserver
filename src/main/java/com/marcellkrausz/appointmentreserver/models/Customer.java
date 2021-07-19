package com.marcellkrausz.appointmentreserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "First name is required")
    @Size(max = 15, message = "First name length should be less then 15 character")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Size(max = 15, message = "Last name length should be less then 15 character")
    private String lastName;
    @NotBlank(message = "Phone number is required.")
    @Size(max = 15, message = "Phone number length should be less then 15 character")
    private String phoneNumber;
    @NotBlank(message = "Email is required.")
    private String email;

    @OneToOne
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Appointment> customerAppointments = new HashSet<>();

    public void addAppointment(Appointment appointment) {
        this.customerAppointments.add(appointment);
    }
}
