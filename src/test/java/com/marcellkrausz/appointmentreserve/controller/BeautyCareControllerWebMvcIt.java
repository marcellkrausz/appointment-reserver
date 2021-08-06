package com.marcellkrausz.appointmentreserve.controller;

import com.marcellkrausz.appointmentreserve.controllers.BeautyCareController;
import com.marcellkrausz.appointmentreserve.models.*;
import com.marcellkrausz.appointmentreserve.services.BeautyCareService;
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
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeautyCareController.class)
public class BeautyCareControllerWebMvcIt {

    @MockBean
    BeautyCareService beautyCareService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testListCosmeticServices() throws Exception {
        BeautyCare beautyCare = new BeautyCare();
        beautyCare.setId(1L);
        beautyCare.setName("Valami");
        beautyCare.setMinutes(30);
        beautyCare.setPrice(3000);

        when(beautyCareService.getAllBeautyCare()).thenReturn(Set.of(beautyCare));

        this.mockMvc.perform(get("/cosmeticservice")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(beautyCare.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(beautyCare.getName())))
                .andExpect(jsonPath("$[0].minutes", is(beautyCare.getMinutes())))
                .andExpect(jsonPath("$[0].price", is(beautyCare.getPrice())));
    }

    @Test
    void testGetCosmeticServiceById() throws Exception {

        BeautyCare beautyCare = new BeautyCare();
        beautyCare.setId(1L);
        beautyCare.setMinutes(21);
        beautyCare.setName("Próba");
        beautyCare.setPrice(21344);

        when(beautyCareService.getBeautyCareById(1L)).thenReturn(beautyCare);

        this.mockMvc.perform(get("/cosmeticservice/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(beautyCare.getId().intValue())))
                .andExpect(jsonPath("$.name", is(beautyCare.getName())))
                .andExpect(jsonPath("$.minutes", is(beautyCare.getMinutes())))
                .andExpect(jsonPath("$.price", is(beautyCare.getPrice())));
    }

    @Test
    void testSaveCosmeticService() throws Exception {
        JSONObject cosmeticJson = new JSONObject();
        cosmeticJson.put("id", 1);
        cosmeticJson.put("name", "Próba1");
        cosmeticJson.put("minutes", 30);
        cosmeticJson.put("price", 3000);

        String json = cosmeticJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/cosmeticservice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateCosmeticService() throws Exception {
        JSONObject cosmeticJson = new JSONObject();
        cosmeticJson.put("id", 1);
        cosmeticJson.put("name", "Próba1");
        cosmeticJson.put("minutes", 30);
        cosmeticJson.put("price", 3000);

        String json = cosmeticJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/cosmeticservice/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteCosmeticServiceById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/cosmeticservice/{id}", "1"))
                .andExpect(status().isNoContent());
    }
}
