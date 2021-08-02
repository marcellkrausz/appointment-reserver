package com.marcellkrausz.appointmentreserve.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Street is required.")
    @Size(max = 150, message = "Street name length should be 150 characters long.")
    private String street;

    private Integer houseNumber;

    @ManyToOne
    private City city;
}
