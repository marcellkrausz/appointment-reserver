package com.marcellkrausz.appointmentreserve.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToMany(mappedBy = "beautyCares", cascade = CascadeType.REMOVE)
    private Set<Appointment> serviceAppointments = new HashSet<>();

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
