package com.marcellkrausz.appointmentreserve.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
