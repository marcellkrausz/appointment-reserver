package com.marcellkrausz.appointmentreserve.exception;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException(String message) {
        super(message);
    }
}
