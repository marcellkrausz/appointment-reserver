package com.marcellkrausz.appointmentreserve.converters;

import com.marcellkrausz.appointmentreserve.models.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserve.models.Appointment;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDtoToAppointment implements Converter<AppointmentDto, Appointment> {

    private final BeautyCareDtoToBeautyCare beautyCareDtoToBeautyCare;
    private final CustomerDtoToCustomer customerDtoToCustomer;

    public AppointmentDtoToAppointment(BeautyCareDtoToBeautyCare beautyCareDtoToBeautyCare, CustomerDtoToCustomer customerDtoToCustomer) {
        this.beautyCareDtoToBeautyCare = beautyCareDtoToBeautyCare;
        this.customerDtoToCustomer = customerDtoToCustomer;
    }

    @Synchronized
    @Nullable
    @Override
    public Appointment convert(AppointmentDto appointmentDto) {
        if (appointmentDto == null) {
            return null;
        }

        final Appointment appointment = new Appointment();
        appointment.setId(appointmentDto.getId());
        appointment.setAppointmentDateStart(appointmentDto.getAppointmentDateStart());
        appointment.setAppointmentDateEnd(appointmentDto.getAppointmentDateEnd());
        appointment.setCustomer(customerDtoToCustomer.convert(appointmentDto.getCustomerDto()));

        if (appointmentDto.getServices() != null && appointmentDto.getServices().size() > 0) {
            appointmentDto.getServices()
                    .forEach(customerServiceCommand -> appointment.getBeautyCares()
                    .add(beautyCareDtoToBeautyCare.convert(customerServiceCommand)));
        }

        return appointment;
    }
}
