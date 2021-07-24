package com.marcellkrausz.appointmentreserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
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
@JsonIgnoreProperties("serviceAppointments")
public class CosmeticService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Cosmetic Service name is required.")
    @Size(min = 5 , max = 150, message = "Cosmetic service name length should be between 5 and 150.")
    private String name;

    private Integer price;

    private Integer minutes;

    @ManyToMany(mappedBy = "cosmeticServices")
    private Set<Appointment> serviceAppointments = new HashSet<>();
}
