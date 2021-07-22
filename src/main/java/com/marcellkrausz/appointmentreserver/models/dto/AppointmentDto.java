package com.marcellkrausz.appointmentreserver.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentDto {

    private Long id;
    private LocalDateTime appointmentDateStart;
    private LocalDateTime appointmentDateEnd;
    private Long customerId;
    private Set<CosmeticServiceDto> services = new HashSet<>();
}
