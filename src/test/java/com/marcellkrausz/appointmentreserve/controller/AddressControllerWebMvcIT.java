package com.marcellkrausz.appointmentreserve.controller;

import com.marcellkrausz.appointmentreserve.controllers.AddressController;
import com.marcellkrausz.appointmentreserve.models.dto.AddressDto;
import com.marcellkrausz.appointmentreserve.services.AddressService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddressController.class)
public class AddressControllerWebMvcIT {

    @MockBean
    AddressService addressService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testListAddress() throws Exception {
        AddressDto address = new AddressDto(1L, "Ady", 23, 1L);
        when(addressService.getAllAddress()).thenReturn(Set.of(address));

        mockMvc.perform(get("/address")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(address.getId().intValue())))
                .andExpect(jsonPath("$[0].street", is(address.getStreet())))
                .andExpect(jsonPath("$[0].houseNumber", is(address.getHouseNumber())))
                .andExpect(jsonPath("$[0].cityId", is(address.getCityId().intValue())));
    }

    @Test
    void testGetAddressById() throws Exception {
        AddressDto address = new AddressDto(1L, "Ady", 23, 1L);
        when(addressService.getAddressById(1L)).thenReturn(address);

        mockMvc.perform(get("/address/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(address.getId().intValue())))
                .andExpect(jsonPath("$.street", is(address.getStreet())))
                .andExpect(jsonPath("$.houseNumber", is(address.getHouseNumber())))
                .andExpect(jsonPath("$.cityId", is(address.getCityId().intValue())));
    }

    @Test
    void testSaveCorrectAddress() throws Exception {
        JSONObject addressJson = new JSONObject();
        addressJson.put("id", 1);
        addressJson.put("street", "Ady");
        addressJson.put("houseNumber", 23);
        addressJson.put("cityId", 1);

        String json = addressJson.toJSONString();

        mockMvc.perform(MockMvcRequestBuilders.post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testSaveAddressWithoutStreetName() throws Exception {
        JSONObject addressJson = new JSONObject();
        addressJson.put("id", 1);
        addressJson.put("houseNumber", 23);
        addressJson.put("cityId", 1);

        String json = addressJson.toJSONString();

        mockMvc.perform(MockMvcRequestBuilders.post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveAddressWithInvalidStreetName() throws Exception {
        JSONObject addressJson = new JSONObject();
        addressJson.put("id", 1);
        addressJson.put("street", "Ad");
        addressJson.put("houseNumber", 23);
        addressJson.put("cityId", 1);

        String json = addressJson.toJSONString();

        mockMvc.perform(MockMvcRequestBuilders.post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveAddressWithInvalidHouseNumber() throws Exception {
        JSONObject addressJson = new JSONObject();
        addressJson.put("id", 1);
        addressJson.put("street", "Ad");
        addressJson.put("houseNumber", null);
        addressJson.put("cityId", 1);

        String json = addressJson.toJSONString();

        mockMvc.perform(MockMvcRequestBuilders.post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateAddress() throws Exception {
        JSONObject addressJson = new JSONObject();
        addressJson.put("id", 1);
        addressJson.put("street", "Ady");
        addressJson.put("houseNumber", 23);
        addressJson.put("cityId", 1);

        String json = addressJson.toJSONString();

        mockMvc.perform(MockMvcRequestBuilders.put("/address/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteAddress() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/address/{id}", "1"))
                .andExpect(status().isNoContent());
    }
}
