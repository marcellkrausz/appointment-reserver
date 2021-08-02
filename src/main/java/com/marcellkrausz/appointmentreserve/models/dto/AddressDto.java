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
public class AddressDto {

    private Long id;
    @NotBlank(message = "Cosmetic Service name is required.")
    @Size(min = 1 , max = 150, message = "Street length should be between 1 and 150.")
    private String street;

    @NotNull
    private Integer houseNumber;
    private Long cityId;
}
