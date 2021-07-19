package com.marcellkrausz.appointmentreserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {

    private Long id;
    private String street;
    private Integer houseNumber;
    private Long cityId;


}
