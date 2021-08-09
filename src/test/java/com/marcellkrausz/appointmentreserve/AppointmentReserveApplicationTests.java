package com.marcellkrausz.appointmentreserve;

import com.marcellkrausz.appointmentreserve.controllers.CityController;
import com.marcellkrausz.appointmentreserve.models.Address;
import com.marcellkrausz.appointmentreserve.models.BeautyCare;
import com.marcellkrausz.appointmentreserve.models.City;
import com.marcellkrausz.appointmentreserve.models.Customer;
import com.marcellkrausz.appointmentreserve.services.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppointmentReserveApplicationTests {

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
    public void testAllFromAddress() {
        List<Address> expected = new ArrayList<>() {{
            add(new Address(1L, "Vörösmarty utca", 1, new City(1L, "Budapest", 1038)));
            add(new Address(2L, "Ady utca", 5, new City(3L, "Budapest", 1122)));
        }};
        ResponseEntity<Address[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/address", Address[].class);
        List<Address> actual = Arrays.asList(responseEntity.getBody());
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testCosmeticServiceById() {
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

    @Test
    public void addNewCity_emptyDatabase_shouldReturnSameCity() {
        City city = new City(null, "Város", 1135);
        restTemplate.postForObject(BASE_URL + "/city", city, City.class);
        City result = restTemplate.getForObject(BASE_URL + "/city/6", City.class);

        assertEquals(result.getName(), city.getName());
    }

    @Test
    public void updateCity_withOnePostedCity_returnsUpdateSong() {
        City city = new City(null, "Város", 1135);
        restTemplate.postForObject(BASE_URL + "/city", city, City.class);

        city.setName("Updated");
        restTemplate.put(BASE_URL + "/city/6", city);

        City updatedCity = restTemplate.getForObject(BASE_URL + "/city/6", City.class);

        assertEquals("Updated", updatedCity.getName());
    }

    @Test
    public void deleteCityById_withSomePostedCity_getAllShouldReturnRemainingCities() {
        Set<City> testCities = Set.of(restTemplate.getForObject(BASE_URL + "/city", City[].class));
        restTemplate.delete(BASE_URL + "/city/1");

        Set<City> remainingCities = Set.of(restTemplate.getForObject(BASE_URL + "/city", City[].class));
        assertEquals(testCities.size(), remainingCities.size() + 1);
    }
}
