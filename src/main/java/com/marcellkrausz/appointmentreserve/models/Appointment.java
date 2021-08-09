package com.marcellkrausz.appointmentreserve.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime appointmentDateStart;
    private LocalDateTime appointmentDateEnd;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "appointment_beauty_care",
    joinColumns = @JoinColumn(name = "appointment_id"),
    inverseJoinColumns = @JoinColumn(name = "beauty_care_id"))
    private Set<BeautyCare> beautyCares = new HashSet<>();

    public Integer getPrice() {
        return beautyCares.stream().mapToInt(BeautyCare::getPrice).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
