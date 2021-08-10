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
@JsonIgnoreProperties("serviceAppointments")
@NoArgsConstructor
public class BeautyCare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private Integer minutes;

    @ManyToMany(mappedBy = "beautyCares")
    private Set<Appointment> serviceAppointments = new HashSet<>();

    public BeautyCare(Long id,
                      @NotBlank(message = "Cosmetic Service name is required.")
                      @Size(min = 5, max = 150, message = "Cosmetic service name length should be between 5 and 150.") String name,
                      Integer price, Integer minutes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.minutes = minutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BeautyCare)) return false;

        BeautyCare that = (BeautyCare) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
