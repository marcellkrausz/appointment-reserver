package com.marcellkrausz.appointmentreserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CosmeticServiceDto {

    private Long id;
    private String name;
    private Integer price;
    private Integer minutes;
}
