package com.marcellkrausz.appointmentreserve.converters;

import com.marcellkrausz.appointmentreserve.models.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserve.models.Appointment;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AppointmentToAppointmentDto implements Converter<Appointment, AppointmentDto> {

    private final BeautyCareToBeautyCareDto beautyCareToBeautyCareDto;
    private final CustomerToCustomerDto customerToCustomerDto;

    public AppointmentToAppointmentDto(BeautyCareToBeautyCareDto beautyCareToBeautyCareDto, CustomerToCustomerDto customerToCustomerDto) {
        this.beautyCareToBeautyCareDto = beautyCareToBeautyCareDto;
        this.customerToCustomerDto = customerToCustomerDto;
    }

    @Synchronized
    @Nullable
    @Override
    public AppointmentDto convert(Appointment appointment) {
        if (appointment == null) {
            return null;
        }

        final AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setAppointmentDateStart(appointment.getAppointmentDateStart());
        appointmentDto.setAppointmentDateEnd(appointment.getAppointmentDateEnd());
        appointmentDto.setCustomerDto(customerToCustomerDto.convert(appointment.getCustomer()));

        if (appointment.getBeautyCares() != null && appointment.getBeautyCares().size() > 0) {
            appointment.getBeautyCares().forEach(cosmeticService -> appointmentDto.getServices().add(beautyCareToBeautyCareDto.convert(cosmeticService)));
        }
        return appointmentDto;
    }

    @Synchronized
    @Nullable
    public Set<AppointmentDto> convertSet(Set<Appointment> appointments) {
        Set<AppointmentDto> appointmentDtos = new HashSet<>();
        for (Appointment appointment : appointments) {
            appointmentDtos.add(convert(appointment));
        }
        return appointmentDtos;
    }
}
