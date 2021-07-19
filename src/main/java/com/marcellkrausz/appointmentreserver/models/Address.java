package com.marcellkrausz.appointmentreserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Street is required.")
    @Size(max = 150, message = "Street name length should be 150 characters long.")
    private String street;
    @NotBlank(message = "House number is required")
    private Integer houseNumber;

    @NotBlank(message = "City is required")
    @ManyToOne
    private City city;
}
