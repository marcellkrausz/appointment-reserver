package com.marcellkrausz.appointmentreserve.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    @NotBlank(message = "First name is required")
    @Size(max = 40, message = "First name length should be less then 40 character")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 40, message = "Last name length should be less then 40 character")
    private String lastName;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    private String email;
    private Long addressId;
}
