package com.marcellkrausz.appointmentreserve.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private Long id;
    @FutureOrPresent
    private LocalDateTime appointmentDateStart;

    @FutureOrPresent
    private LocalDateTime appointmentDateEnd;
    private CustomerDto customerDto;
    private Set<BeautyCareDto> services = new HashSet<>();
}
