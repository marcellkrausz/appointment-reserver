package com.marcellkrausz.appointmentreserve.model.dto;

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
    @NotNull
    @NotBlank(message = "{city.name}")
    @Size(min = 5 , max = 150, message = "City name length should be between 5 and 150.")
    private String name;

    @NotNull
    private Integer postalCode;
}
