package com.marcellkrausz.appointmentreserve;

import com.marcellkrausz.appointmentreserve.models.Address;
import com.marcellkrausz.appointmentreserve.models.BeautyCare;
import com.marcellkrausz.appointmentreserve.models.City;
import com.marcellkrausz.appointmentreserve.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppointmentReserveApplicationIT {

    @LocalServerPort
    private Integer port;

    private String BASE_URL;

    private static TestRestTemplate restTemplate = new TestRestTemplate();


    @BeforeEach
    public void init() {
        BASE_URL = "http://localhost:" + port;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testBeautyCareById() {
        BeautyCare excepted = new BeautyCare(1L, "Nyomásterápia", 3000, 30);
        ResponseEntity<BeautyCare> responseEntity = restTemplate.getForEntity(BASE_URL + "/beautycare/1", BeautyCare.class);
        BeautyCare actual = responseEntity.getBody();

        assertEquals(excepted.getName(), actual.getName());
    }

    @Test
    public void testGetCustomerById() {
        Customer expected = new Customer(2L, "Zsolt", "Zemen", "06709823366", "zemenzsolt@gmail.com");
        ResponseEntity<Customer> responseEntity = restTemplate.getForEntity(BASE_URL + "/customer/2", Customer.class);
        Customer actual = responseEntity.getBody();

        assertEquals(expected.getLastName(), actual.getLastName());
    }
}
