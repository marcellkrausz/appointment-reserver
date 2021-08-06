package com.marcellkrausz.appointmentreserve.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeautyCareDto {

    private Long id;
    @NotBlank(message = "Cosmetic Service name is required.")
    @Size(min = 5 , max = 150, message = "Cosmetic service name length should be between 5 and 150.")
    private String name;

    @Min(value = 1000, message = "Price is minimum 1000HUF")
    private Integer price;

    @Min(value = 10, message = "Time is minimum 10 minutes")
    private Integer minutes;
}
