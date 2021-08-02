package com.marcellkrausz.appointmentreserve.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@JsonIgnoreProperties("addresses")
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "City name is required.")
    private String name;

    private Integer postalCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city")
    private Set<Address> addresses = new HashSet<>();

    public City(Long id, @NotBlank(message = "City name is required.") String name, @NotBlank(message = "Postal code is required.") int postalCode) {
        this.id = id;
        this.name = name;
        this.postalCode = postalCode;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }
}
