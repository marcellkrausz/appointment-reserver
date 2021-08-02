package com.marcellkrausz.appointmentreserve.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {

    private Long id;
    @NotBlank(message = "City name is required.")
    @Size(min = 5 , max = 150, message = "City name length should be between 5 and 150.")
    private String name;

    @NotNull
    private Integer postalCode;
}