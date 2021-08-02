package com.marcellkrausz.appointmentreserve;

import com.marcellkrausz.appointmentreserve.controllers.CosmeticServiceController;
import com.marcellkrausz.appointmentreserve.models.*;
import com.marcellkrausz.appointmentreserve.services.CosmeticServiceService;
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

@WebMvcTest(CosmeticServiceController.class)
public class CosmeticServiceControllerWebMvcIt {

    @MockBean
    CosmeticServiceService cosmeticServiceService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testListCosmeticServices() throws Exception {
        CosmeticService cosmeticService = new CosmeticService();
        cosmeticService.setId(1L);
        cosmeticService.setName("Valami");
        cosmeticService.setMinutes(30);
        cosmeticService.setPrice(3000);

        when(cosmeticServiceService.getAllCosmeticService()).thenReturn(Set.of(cosmeticService));

        this.mockMvc.perform(get("/cosmeticservice")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(cosmeticService.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(cosmeticService.getName())))
                .andExpect(jsonPath("$[0].minutes", is(cosmeticService.getMinutes())))
                .andExpect(jsonPath("$[0].price", is(cosmeticService.getPrice())));
    }

    @Test
    void testGetCosmeticServiceById() throws Exception {

        CosmeticService cosmeticService = new CosmeticService();
        cosmeticService.setId(1L);
        cosmeticService.setMinutes(21);
        cosmeticService.setName("Próba");
        cosmeticService.setPrice(21344);

        when(cosmeticServiceService.getCosmeticServiceById(1L)).thenReturn(cosmeticService);

        this.mockMvc.perform(get("/cosmeticservice/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(cosmeticService.getId().intValue())))
                .andExpect(jsonPath("$.name", is(cosmeticService.getName())))
                .andExpect(jsonPath("$.minutes", is(cosmeticService.getMinutes())))
                .andExpect(jsonPath("$.price", is(cosmeticService.getPrice())));
    }

    @Test
    void testSaveCosmeticService() throws Exception {
        JSONObject cosmeticJson = new JSONObject();
        cosmeticJson.put("id", 1);
        cosmeticJson.put("name", "Próba");
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
    void testDeleteCosmeticServiceById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/cosmeticservice/{id}", "1"))
                .andExpect(status().isNoContent());
    }
}
