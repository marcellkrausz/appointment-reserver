package com.marcellkrausz.appointmentreserve.controller;

import com.marcellkrausz.appointmentreserve.model.dto.BeautyCareDto;
import com.marcellkrausz.appointmentreserve.service.BeautyCareService;
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
public class BeautyCareControllerWebMvcUT {

    @MockBean
    BeautyCareService beautyCareService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testListBeautyCares() throws Exception {
        BeautyCareDto beautyCare = new BeautyCareDto();
        beautyCare.setId(1L);
        beautyCare.setName("Valami");
        beautyCare.setMinutes(30);
        beautyCare.setPrice(3000);

        when(beautyCareService.getAllBeautyCare()).thenReturn(Set.of(beautyCare));

        this.mockMvc.perform(get("/beautycare")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(beautyCare.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(beautyCare.getName())))
                .andExpect(jsonPath("$[0].minutes", is(beautyCare.getMinutes())))
                .andExpect(jsonPath("$[0].price", is(beautyCare.getPrice())));
    }

    @Test
    void testGetBeautyCareById() throws Exception {
        BeautyCareDto beautyCare = new BeautyCareDto();
        beautyCare.setId(1L);
        beautyCare.setMinutes(21);
        beautyCare.setName("Próba");
        beautyCare.setPrice(21344);

        when(beautyCareService.getBeautyCareById(1L)).thenReturn(beautyCare);

        this.mockMvc.perform(get("/beautycare/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(beautyCare.getId().intValue())))
                .andExpect(jsonPath("$.name", is(beautyCare.getName())))
                .andExpect(jsonPath("$.minutes", is(beautyCare.getMinutes())))
                .andExpect(jsonPath("$.price", is(beautyCare.getPrice())));
    }

    @Test
    void testGetBeautyCareByInvalidId() throws Exception {
        mockMvc.perform(get("/beautycare/a")).andExpect(status().isBadRequest());
    }

    @Test
    void testSaveBeautyCare() throws Exception {
        JSONObject beautyJson = new JSONObject();
        beautyJson.put("id", 1);
        beautyJson.put("name", "Próba1");
        beautyJson.put("minutes", 30);
        beautyJson.put("price", 3000);

        String json = beautyJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/beautycare")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testSaveBeautyCareWithInvalidName() throws Exception {
        JSONObject beautyJson = new JSONObject();
        beautyJson.put("id", 1);
        beautyJson.put("name", "Bad");
        beautyJson.put("minutes", 30);
        beautyJson.put("price", 3000);

        String json = beautyJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/beautycare")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveBeautyCareWithInvalidTime() throws Exception {
        JSONObject beautyJson = new JSONObject();
        beautyJson.put("id", 1);
        beautyJson.put("name", "Próba1");
        beautyJson.put("minutes", 7);
        beautyJson.put("price", 3000);

        String json = beautyJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/beautycare")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveBeautyCareWithInvalidPrice() throws Exception {
        JSONObject beautyJson = new JSONObject();
        beautyJson.put("id", 1);
        beautyJson.put("name", "Próba1");
        beautyJson.put("minutes", 30);
        beautyJson.put("price", 500);

        String json = beautyJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/beautycare")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateBeautyCare() throws Exception {
        JSONObject beautyJson = new JSONObject();
        beautyJson.put("id", 1);
        beautyJson.put("name", "Próba1");
        beautyJson.put("minutes", 30);
        beautyJson.put("price", 3000);

        String json = beautyJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/beautycare/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteBeautyCareById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/beautycare/{id}", "1"))
                .andExpect(status().isNoContent());
    }
}
