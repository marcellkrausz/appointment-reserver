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
public class CustomerDto {

    private Long id;
    @NotNull
    @NotBlank(message = "{customer.firstName}")
    @Size(max = 40, message = "First name length should be less then 40 character")
    private String firstName;

    @NotNull
    @NotBlank(message = "{customer.lastName}")
    @Size(max = 40, message = "Last name length should be less then 40 character")
    private String lastName;

    @NotNull
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotNull
    @NotBlank(message = "Email is required")
    private String email;
    @NotNull
    private Long addressId;
}
