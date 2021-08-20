package com.marcellkrausz.appointmentreserve.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private Long id;
    @NotNull
    @FutureOrPresent
    private LocalDateTime appointmentDateStart;

    @NotNull
    @FutureOrPresent
    private LocalDateTime appointmentDateEnd;
    private CustomerDto customerDto;
    private Set<BeautyCareDto> services = new HashSet<>();
}
