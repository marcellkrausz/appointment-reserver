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
    @NotNull
    @NotBlank(message = "{address.name}")
    @Size(min = 3 , max = 150, message = "Street length should be between 1 and 150.")
    private String street;

    @NotNull
    private Integer houseNumber;
    @NotNull
    private Long cityId;
}
