package com.marcellkrausz.appointmentreserve.integration;

import com.marcellkrausz.appointmentreserve.model.dto.AddressDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
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
        List<AddressDto> expected = new ArrayList<>() {{
            add(new AddressDto(1L, "Vörösmarty utca", 1, 1L));
            add(new AddressDto(2L, "Ady utca", 5, 2L));
        }};
        ResponseEntity<AddressDto[]> responseEntity = restTemplate.getForEntity(BASE_URL, AddressDto[].class);
        List<AddressDto> actual = Arrays.asList(responseEntity.getBody());
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testGetAddressById() {
        AddressDto expected = new AddressDto(1L, "Vörösmarty utca", 1, 1L);
        ResponseEntity<AddressDto> responseEntity = restTemplate.getForEntity(BASE_URL + "/1", AddressDto.class);
        AddressDto actual = responseEntity.getBody();

        assertEquals(expected.getStreet(), actual.getStreet());
    }

    @Test
    public void addNewAddress_shouldReturnSameAddress() {
        AddressDto addressDto = new AddressDto(null, "Jankovics", 1, 1L);

        restTemplate.postForObject(BASE_URL, addressDto, AddressDto.class);
        AddressDto result = restTemplate.getForObject(BASE_URL + "/3", AddressDto.class);

        assertEquals(addressDto.getStreet(), result.getStreet());
    }


}
