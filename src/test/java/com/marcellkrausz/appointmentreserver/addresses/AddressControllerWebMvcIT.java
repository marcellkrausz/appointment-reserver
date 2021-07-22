package com.marcellkrausz.appointmentreserver.addresses;

import com.marcellkrausz.appointmentreserver.controllers.AddressController;
import com.marcellkrausz.appointmentreserver.models.Address;
import com.marcellkrausz.appointmentreserver.models.City;
import com.marcellkrausz.appointmentreserver.services.AddressService;
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
        City city = new City();
        city.setId(1L);
        city.setName("Sülysáp");
        city.setPostalCode(4000);

        Address address = new Address(1L, "Ady", 23, city);
        when(addressService.getAllAddress()).thenReturn(Set.of(address));

        mockMvc.perform(get("/address")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(address.getId().intValue())))
                .andExpect(jsonPath("$[0].street", is(address.getStreet())))
                .andExpect(jsonPath("$[0].houseNumber", is(address.getHouseNumber())))
                .andExpect(jsonPath("$[0].city.id", is(address.getCity().getId().intValue())));
    }

    @Test
    void testGetAddressById() throws Exception {
        City city = new City();
        city.setId(1L);
        city.setName("Sülysáp");
        city.setPostalCode(4000);

        Address address = new Address(1L, "Ady", 23, city);
        when(addressService.getAddressById(1L)).thenReturn(address);

        mockMvc.perform(get("/address/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(address.getId().intValue())))
                .andExpect(jsonPath("$.street", is(address.getStreet())))
                .andExpect(jsonPath("$.houseNumber", is(address.getHouseNumber())))
                .andExpect(jsonPath("$.city.id", is(address.getCity().getId().intValue())));
    }

    @Test
    void testSaveAddress() throws Exception {

        JSONObject cityJson = new JSONObject();
        cityJson.put("id", 1);
        cityJson.put("name", "Sülysáp");
        cityJson.put("postalCode", 1122);

        JSONObject addressJson = new JSONObject();
        addressJson.put("id", 1);
        addressJson.put("street", "Ady");
        addressJson.put("houseNumber", 23);
        addressJson.put("city", cityJson);

        String json = addressJson.toJSONString();

        mockMvc.perform(MockMvcRequestBuilders.post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                        .andExpect(header().string("Address", "/address"))
//                        .andExpect(jsonPath("$.id").value("1"))
//                        .andExpect(jsonPath("$.street").value("Ady"))
//                        .andExpect(jsonPath("$.houseNumber").value("23"))
//                        .andExpect(jsonPath("$.city.id").value("1"))
//                        .andExpect(jsonPath("$.city.name").value("Sülysáp"))
//                        .andExpect(jsonPath("$.city.postalCode").value("1122"));
    }

    @Test
    void testDeleteAddress() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/address/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
