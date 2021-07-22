package com.marcellkrausz.appointmentreserver.converters;

import com.marcellkrausz.appointmentreserver.models.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserver.models.Appointment;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AppointmentToAppointmentDto implements Converter<Appointment, AppointmentDto> {

    private final CosmeticServiceToCosmeticServiceDto cosmeticServiceToCosmeticServiceDto;

    public AppointmentToAppointmentDto(CosmeticServiceToCosmeticServiceDto cosmeticServiceToCosmeticServiceDto) {
        this.cosmeticServiceToCosmeticServiceDto = cosmeticServiceToCosmeticServiceDto;
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
        appointmentDto.setAppointmentDateStart(appointmentDto.getAppointmentDateStart());
        appointmentDto.setAppointmentDateEnd(appointmentDto.getAppointmentDateEnd());

        if (appointment.getCustomer() != null) {
            appointmentDto.setCustomerId(appointment.getCustomer().getId());
        }

        if (appointment.getCosmeticServices() != null && appointment.getCosmeticServices().size() > 0) {
            appointment.getCosmeticServices().forEach(cosmeticService -> appointmentDto.getServices().add(cosmeticServiceToCosmeticServiceDto.convert(cosmeticService)));
        }
        return appointmentDto;
    }
}
