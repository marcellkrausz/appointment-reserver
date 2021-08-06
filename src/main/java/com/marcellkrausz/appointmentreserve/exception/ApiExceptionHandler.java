package com.marcellkrausz.appointmentreserve.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {
            AddressNotFoundException.class,
            AppointmentNotFoundException.class,
            CityNotFoundException.class,
            BeautyCareNotFoundException.class,
            CustomerNotFoundException.class})

    public ResponseEntity<Object> handleApiRequestException(RuntimeException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, badRequest);
    }

}
