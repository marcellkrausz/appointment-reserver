package com.marcellkrausz.appointmentreserve.converters;

import com.marcellkrausz.appointmentreserve.models.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserve.models.Appointment;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDtoToAppointment implements Converter<AppointmentDto, Appointment> {

    private final CosmeticServiceDtoToCosmeticService cosmeticServiceDtoToCosmeticService;
    private final CustomerDtoToCustomer customerDtoToCustomer;

    public AppointmentDtoToAppointment(CosmeticServiceDtoToCosmeticService cosmeticServiceDtoToCosmeticService, CustomerDtoToCustomer customerDtoToCustomer) {
        this.cosmeticServiceDtoToCosmeticService = cosmeticServiceDtoToCosmeticService;
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
                    .forEach(customerServiceCommand -> appointment.getCosmeticServices()
                    .add(cosmeticServiceDtoToCosmeticService.convert(customerServiceCommand)));
        }

        return appointment;
    }
}
