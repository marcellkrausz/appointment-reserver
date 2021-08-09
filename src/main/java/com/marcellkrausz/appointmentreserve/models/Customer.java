package com.marcellkrausz.appointmentreserve.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@EqualsAndHashCode
@NoArgsConstructor
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

    public Customer(Long id,
                    @NotBlank(message = "First name is required") @Size(max = 15, message = "First name length should be less then 15 character") String firstName,
                    @NotBlank(message = "Last name is required") @Size(max = 15, message = "Last name length should be less then 15 character") String lastName,
                    @NotBlank(message = "Phone number is required.") @Size(max = 15, message = "Phone number length should be less then 15 character") String phoneNumber,
                    @NotBlank(message = "Email is required.") String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
