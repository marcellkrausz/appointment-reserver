package com.marcellkrausz.appointmentreserver.cities;

import com.marcellkrausz.appointmentreserver.controllers.CityController;
import com.marcellkrausz.appointmentreserver.models.City;
import com.marcellkrausz.appointmentreserver.services.CityService;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CityController.class)
public class CityControllerWebMvcIt {

    @MockBean
    CityService cityService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testListCities() throws Exception {
        City city = new City();
        city.setId(1L);
        city.setName("Sülysáp");
        city.setPostalCode(4000);

        when(cityService.getAllCities()).thenReturn(Set.of(city));

        mockMvc.perform(get("/city")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(city.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(city.getName())))
                .andExpect(jsonPath("$[0].postalCode", is(city.getPostalCode())));
    }

    @Test
    void testCityById() throws Exception {
        City city = new City();
        city.setId(1L);
        city.setName("Sülysáp");
        city.setPostalCode(4000);

        when(cityService.getCityById(1L)).thenReturn(city);

        mockMvc.perform(get("/city/{id}", 1)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(city.getId().intValue())))
                .andExpect(jsonPath("$.name", is(city.getName())))
                .andExpect(jsonPath("$.postalCode", is(city.getPostalCode())));
    }

    @Test
    void testSaveCity() throws Exception {
        JSONObject cityJson = new JSONObject();
        cityJson.put("id", 1);
        cityJson.put("name", "Sülysáp");
        cityJson.put("postalCode", 4000);

        String json = cityJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/city")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCityById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/city/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
