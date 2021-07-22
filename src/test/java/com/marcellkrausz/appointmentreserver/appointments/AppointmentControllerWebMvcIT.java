package com.marcellkrausz.appointmentreserver.appointments;

import com.marcellkrausz.appointmentreserver.controllers.AppointmentController;
import com.marcellkrausz.appointmentreserver.models.*;
import com.marcellkrausz.appointmentreserver.services.AppointmentService;

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
        City city = new City();
        city.setId(1L);
        city.setName("Sülysáp");
        city.setPostalCode(4000);

        Address address = new Address(1L, "Ady", 23, city);

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Ádám");
        customer.setLastName("Nagy");
        customer.setEmail("adam@gmail.com");
        customer.setPhoneNumber("06305554646");
        customer.setAddress(address);

        CosmeticService cosmeticService = new CosmeticService();
        cosmeticService.setId(1l);
        cosmeticService.setMinutes(21);
        cosmeticService.setName("Próba");
        cosmeticService.setPrice(21344);

        Appointment appointment = new Appointment(1L
                , LocalDateTime.of(2021, 07, 18, 15, 00, 00)
                , LocalDateTime.of(2021, 07, 18, 14, 00, 00)
                , customer
                , Set.of(cosmeticService));
        when(appointmentService.getAllAppointment()).thenReturn(Set.of(appointment));

        mockMvc.perform(get("/appointments")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(appointment.getId().intValue())))
                .andExpect(jsonPath("$[0].appointmentDateStart", is("2021-07-18T15:00:00")))
                .andExpect(jsonPath("$[0].appointmentDateEnd", is("2021-07-18T14:00:00")))
                .andExpect(jsonPath("$[0].customer.id", is(customer.getId().intValue())))
                .andExpect(jsonPath("$[0].cosmeticServices[0].id", is(cosmeticService.getId().intValue())));

    }

    @Test
    void testGetAppointmentById() throws Exception {
        City city = new City();
        city.setId(1L);
        city.setName("Sülysáp");
        city.setPostalCode(4000);

        Address address = new Address(1L, "Ady", 23, city);

        Customer customer = new Customer();
        customer.setId(1l);
        customer.setFirstName("Ádám");
        customer.setLastName("Nagy");
        customer.setEmail("adam@gmail.com");
        customer.setPhoneNumber("06305554646");
        customer.setAddress(address);

        CosmeticService cosmeticService = new CosmeticService();
        cosmeticService.setId(1L);
        cosmeticService.setMinutes(21);
        cosmeticService.setName("Próba");
        cosmeticService.setPrice(21344);

        Appointment appointment = new Appointment(1L
                , LocalDateTime.of(2021, 07, 18, 15, 00, 00)
                , LocalDateTime.of(2021, 07, 18, 14, 00, 00)
                , customer
                , Set.of(cosmeticService));
        when(appointmentService.getAppointmentById(1L)).thenReturn(appointment);

        mockMvc.perform(get("/appointments/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(appointment.getId().intValue())))
                .andExpect(jsonPath("$.appointmentDateStart", is("2021-07-18T15:00:00")))
                .andExpect(jsonPath("$.appointmentDateEnd", is("2021-07-18T14:00:00")))
                .andExpect(jsonPath("$.customer.id", is(customer.getId().intValue())))
                .andExpect(jsonPath("$.cosmeticServices[0].id", is(cosmeticService.getId().intValue())));
    }

    @Test
    void testSaveAppointment() throws Exception {
        JSONObject cityJson = new JSONObject();
        cityJson.put("id", 1);
        cityJson.put("name", "Sülysáp");
        cityJson.put("postalCode", 1122);

        JSONObject addressJson = new JSONObject();
        addressJson.put("id", 1);
        addressJson.put("street", "Ady");
        addressJson.put("houseNumber", 23);
        addressJson.put("city", cityJson);

        JSONObject customerJson = new JSONObject();
        customerJson.put("id", 1);
        customerJson.put("firstName", "Ádám");
        customerJson.put("lastName", "Nagy");
        customerJson.put("email", "adam@gmail.com");
        customerJson.put("phoneNumber", "06305554646");
        customerJson.put("address", addressJson);

        JSONObject cosmeticServiceJson = new JSONObject();
        cosmeticServiceJson.put("id", 1);
        cosmeticServiceJson.put("minutes", 21);
        cosmeticServiceJson.put("name", "Próba");
        cosmeticServiceJson.put("price", 21344);

        JSONObject appointmentJson = new JSONObject();
        appointmentJson.put("id", 1);
        appointmentJson.put("appointmentDateStart", "2021-07-18T15:00:00");
        appointmentJson.put("appointmentDateEnd", "2021-07-18T14:00:00");
        appointmentJson.put("customer", customerJson);
        appointmentJson.put("cosmeticServices", Set.of(cosmeticServiceJson));

        String json = appointmentJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/appointments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAppointment() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/appointments/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
