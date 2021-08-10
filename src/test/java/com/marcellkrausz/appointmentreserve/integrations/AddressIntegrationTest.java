package com.marcellkrausz.appointmentreserve.integrations;

import com.marcellkrausz.appointmentreserve.models.Address;
import com.marcellkrausz.appointmentreserve.models.City;
import com.marcellkrausz.appointmentreserve.models.dto.AddressDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddressIntegrationTest {

    @LocalServerPort
    private Integer port;

    private String BASE_URL;

    private static TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeEach
    public void init() {
        BASE_URL = "http://localhost:" + port + "/address";
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
    public void testGetAddressById() {
        Address expected = new Address(1L, "Vörösmarty utca", 1, new City(1L, "Budapest", 1038));
        ResponseEntity<Address> responseEntity = restTemplate.getForEntity(BASE_URL + "/1", Address.class);
        Address actual = responseEntity.getBody();

        assertEquals(expected, actual);
    }

    @Test
    public void addNewAddress_shouldReturnSameAddress() {
        AddressDto testAddress = new AddressDto(null, "Jankovics", 1, 1L);

        restTemplate.postForObject(BASE_URL, testAddress, Address.class);
        Address result = restTemplate.getForObject(BASE_URL + "/3", Address.class);

        assertEquals(testAddress.getStreet(), result.getStreet());
    }
}
