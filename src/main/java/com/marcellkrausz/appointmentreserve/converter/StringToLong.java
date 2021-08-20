package com.marcellkrausz.appointmentreserve.converter;

import org.springframework.stereotype.Component;

@Component
public class StringToLong {

    public static Long convert(String id) {
        try {
            return Long.parseLong(id);
        } catch (Exception ex) {
            return null;
        }
    }
}
