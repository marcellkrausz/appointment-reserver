package com.marcellkrausz.appointmentreserve.controller;

import com.marcellkrausz.appointmentreserve.controllers.AppointmentController;
import com.marcellkrausz.appointmentreserve.exception.AppointmentNotFoundException;
import com.marcellkrausz.appointmentreserve.models.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserve.models.dto.BeautyCareDto;
import com.marcellkrausz.appointmentreserve.models.dto.CustomerDto;
import com.marcellkrausz.appointmentreserve.services.AppointmentService;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Set;


@WebMvcTest(AppointmentController.class)
public class AppointmentControllerWebMvcIT {

    @MockBean
    AppointmentService appointmentService;

    @Autowired
    MockMvc mockMvc;


    @Test
    void testListAppointments() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1l);
        customerDto.setFirstName("Ádám");
        customerDto.setLastName("Nagy");
        customerDto.setEmail("adam@gmail.com");
        customerDto.setPhoneNumber("06305554646");
        customerDto.setAddressId(1L);

        BeautyCareDto cosmeticService = new BeautyCareDto();
        cosmeticService.setId(1l);
        cosmeticService.setMinutes(21);
        cosmeticService.setName("Próba");
        cosmeticService.setPrice(21344);

        AppointmentDto appointment = new AppointmentDto(1L
                , LocalDateTime.of(2021, 07, 18, 15, 00, 00)
                , LocalDateTime.of(2021, 07, 18, 14, 00, 00)
                , customerDto
                , Set.of(cosmeticService));
        when(appointmentService.getAllAppointment()).thenReturn(Set.of(appointment));

        this.mockMvc.perform(get("/appointment")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(appointment.getId().intValue())))
                .andExpect(jsonPath("$[0].appointmentDateStart", is("2021-07-18T15:00:00")))
                .andExpect(jsonPath("$[0].appointmentDateEnd", is("2021-07-18T14:00:00")))
                .andExpect(jsonPath("$[0].customerDto.id", is(customerDto.getId().intValue())))
                .andExpect(jsonPath("$[0].services[0].id", is(cosmeticService.getId().intValue())));

    }

    @Test
    void testGetAppointmentById() throws Exception {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1l);
        customerDto.setFirstName("Ádám");
        customerDto.setLastName("Nagy");
        customerDto.setEmail("adam@gmail.com");
        customerDto.setPhoneNumber("06305554646");
        customerDto.setAddressId(1L);

        BeautyCareDto cosmeticService = new BeautyCareDto();
        cosmeticService.setId(1L);
        cosmeticService.setMinutes(21);
        cosmeticService.setName("Próba");
        cosmeticService.setPrice(21344);

        AppointmentDto appointment = new AppointmentDto(1L
                , LocalDateTime.of(2021, 07, 18, 15, 00, 00)
                , LocalDateTime.of(2021, 07, 18, 14, 00, 00)
                , customerDto
                , Set.of(cosmeticService));

        when(appointmentService.getAppointmentById(1L)).thenReturn(appointment);

        this.mockMvc.perform(get("/appointment/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(appointment.getId().intValue())))
                .andExpect(jsonPath("$.appointmentDateStart", is("2021-07-18T15:00:00")))
                .andExpect(jsonPath("$.appointmentDateEnd", is("2021-07-18T14:00:00")))
                .andExpect(jsonPath("$.customerDto.id", is(customerDto.getId().intValue())))
                .andExpect(jsonPath("$.services[0].id", is(cosmeticService.getId().intValue())));
    }

    @Test
    void testGetAddressWithInvalidId() throws Exception {
        when(appointmentService.getAppointmentById(1L)).thenThrow(AppointmentNotFoundException.class);

        mockMvc.perform(get("/appointment/1")).andExpect(status().isBadRequest());
    }

    @Test
    void testSaveAppointment() throws Exception {

        JSONObject customerJson = new JSONObject();
        customerJson.put("id", 1);
        customerJson.put("firstName", "Ádám");
        customerJson.put("lastName", "Nagy");
        customerJson.put("email", "adam@gmail.com");
        customerJson.put("phoneNumber", "06305554646");
        customerJson.put("addressId", 1);

        JSONObject cosmeticServiceJson = new JSONObject();
        cosmeticServiceJson.put("id", 1);
        cosmeticServiceJson.put("minutes", 21);
        cosmeticServiceJson.put("name", "Próba");
        cosmeticServiceJson.put("price", 21344);

        JSONObject appointmentJson = new JSONObject();
        appointmentJson.put("id", 1);
        appointmentJson.put("appointmentDateStart", "2022-07-18T15:00:00");
        appointmentJson.put("appointmentDateEnd", "2022-07-18T14:00:00");
        appointmentJson.put("customer", customerJson);
        appointmentJson.put("cosmeticServices", Set.of(cosmeticServiceJson));

        String json = appointmentJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testSaveAppointmentWithInvalidDateStart() throws Exception {
        JSONObject customerJson = new JSONObject();
        customerJson.put("id", 1);
        customerJson.put("firstName", "Ádám");
        customerJson.put("lastName", "Nagy");
        customerJson.put("email", "adam@gmail.com");
        customerJson.put("phoneNumber", "06305554646");
        customerJson.put("addressId", 1);

        JSONObject cosmeticServiceJson = new JSONObject();
        cosmeticServiceJson.put("id", 1);
        cosmeticServiceJson.put("minutes", 21);
        cosmeticServiceJson.put("name", "Próba");
        cosmeticServiceJson.put("price", 21344);

        JSONObject appointmentJson = new JSONObject();
        appointmentJson.put("id", 1);
        appointmentJson.put("appointmentDateStart", "2021-07-18T15:00:00");
        appointmentJson.put("appointmentDateEnd", "2022-07-18T14:00:00");
        appointmentJson.put("customer", customerJson);
        appointmentJson.put("cosmeticServices", Set.of(cosmeticServiceJson));

        String json = appointmentJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveAppointmentWithInvalidDateEnd() throws Exception {
        JSONObject customerJson = new JSONObject();
        customerJson.put("id", 1);
        customerJson.put("firstName", "Ádám");
        customerJson.put("lastName", "Nagy");
        customerJson.put("email", "adam@gmail.com");
        customerJson.put("phoneNumber", "06305554646");
        customerJson.put("addressId", 1);

        JSONObject cosmeticServiceJson = new JSONObject();
        cosmeticServiceJson.put("id", 1);
        cosmeticServiceJson.put("minutes", 21);
        cosmeticServiceJson.put("name", "Próba");
        cosmeticServiceJson.put("price", 21344);

        JSONObject appointmentJson = new JSONObject();
        appointmentJson.put("id", 1);
        appointmentJson.put("appointmentDateStart", "2022-07-18T15:00:00");
        appointmentJson.put("appointmentDateEnd", "2021-07-18T14:00:00");
        appointmentJson.put("customer", customerJson);
        appointmentJson.put("cosmeticServices", Set.of(cosmeticServiceJson));

        String json = appointmentJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/appointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateAppointment() throws Exception {
        JSONObject customerJson = new JSONObject();
        customerJson.put("id", 1);
        customerJson.put("firstName", "Ádám");
        customerJson.put("lastName", "Nagy");
        customerJson.put("email", "adam@gmail.com");
        customerJson.put("phoneNumber", "06305554646");
        customerJson.put("addressId", 1);

        JSONObject cosmeticServiceJson = new JSONObject();
        cosmeticServiceJson.put("id", 1);
        cosmeticServiceJson.put("minutes", 21);
        cosmeticServiceJson.put("name", "Próba");
        cosmeticServiceJson.put("price", 21344);

        JSONObject appointmentJson = new JSONObject();
        appointmentJson.put("id", 1);
        appointmentJson.put("appointmentDateStart", "2022-07-18T15:00:00");
        appointmentJson.put("appointmentDateEnd", "2022-07-18T14:00:00");
        appointmentJson.put("customer", customerJson);
        appointmentJson.put("cosmeticServices", Set.of(cosmeticServiceJson));

        String json = appointmentJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders.put("/appointment/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteAppointment() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/appointment/{id}", "1"))
                .andExpect(status().isNoContent());
    }
}
