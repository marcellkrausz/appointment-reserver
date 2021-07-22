package com.marcellkrausz.appointmentreserver.converters;

import com.marcellkrausz.appointmentreserver.models.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserver.models.Appointment;
import com.marcellkrausz.appointmentreserver.models.Customer;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDtoToAppointment implements Converter<AppointmentDto, Appointment> {

    private final CosmeticServiceDtoToCosmeticService cosmeticServiceDtoToCosmeticService;

    public AppointmentDtoToAppointment(CosmeticServiceDtoToCosmeticService cosmeticServiceDtoToCosmeticService) {
        this.cosmeticServiceDtoToCosmeticService = cosmeticServiceDtoToCosmeticService;
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

        if (appointmentDto.getCustomerId() != 0) {
            Customer customer = new Customer();
            customer.setId(appointmentDto.getCustomerId());
            appointment.setCustomer(customer);
            customer.addAppointment(appointment);
        }

        if (appointmentDto.getServices() != null && appointmentDto.getServices().size() > 0) {
            appointmentDto.getServices()
                    .forEach(customerServiceCommand -> appointment.getCosmeticServices()
                    .add(cosmeticServiceDtoToCosmeticService.convert(customerServiceCommand)));
        }

        return appointment;
    }
}
