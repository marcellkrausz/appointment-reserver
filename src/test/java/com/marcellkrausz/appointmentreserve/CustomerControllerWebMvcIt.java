package com.marcellkrausz.appointmentreserve;

import com.marcellkrausz.appointmentreserve.controllers.CustomerController;
import com.marcellkrausz.appointmentreserve.models.Customer;
import com.marcellkrausz.appointmentreserve.services.CustomerService;
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

@WebMvcTest(CustomerController.class)
public class CustomerControllerWebMvcIt {

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testListCustomers() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Marcell");
        customer.setLastName("Krausz");
        customer.setPhoneNumber("067894444");
        customer.setEmail("marcell@gmail.com");

        when(customerService.getAllCustomer()).thenReturn(Set.of(customer));

        this.mockMvc.perform(get("/customer")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(customer.getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(customer.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(customer.getLastName())))
                .andExpect(jsonPath("$[0].phoneNumber", is(customer.getPhoneNumber())))
                .andExpect(jsonPath("$[0].email", is(customer.getEmail())));
    }

    @Test
    void testGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Marcell");
        customer.setLastName("Krausz");
        customer.setPhoneNumber("067894444");
        customer.setEmail("marcell@gmail.com");

        when(customerService.getCustomerById(1L)).thenReturn(customer);

        this.mockMvc.perform(get("/customer/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(customer.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(customer.getLastName())))
                .andExpect(jsonPath("$.phoneNumber", is(customer.getPhoneNumber())))
                .andExpect(jsonPath("$.email", is(customer.getEmail())));
    }

    @Test
    void testSaveCustomer() throws Exception {
        JSONObject customerJson = new JSONObject();
        customerJson.put("id", 1);
        customerJson.put("firstName", "Marcell");
        customerJson.put("lastName", "Krausz");
        customerJson.put("phoneNumber", "067894444");
        customerJson.put("email", "marcell@gmail.com");

        String json = customerJson.toJSONString();

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testDeleteCustomerById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/customer/{id}", "1"))
                .andExpect(status().isNoContent());
    }
}
