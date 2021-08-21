package com.marcellkrausz.appointmentreserve.integration;

import com.marcellkrausz.appointmentreserve.model.City;
import com.marcellkrausz.appointmentreserve.model.dto.CityDto;
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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CityIntegrationTest {

    @LocalServerPort
    private Integer port;

    private String BASE_URL;

    private static TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeEach
    public void init() {
        BASE_URL = "http://localhost:" + port + "/city";
    }

    @Test
    public void testAllFromCity() {
        List<CityDto> expected = new ArrayList<>() {{
            add(new CityDto(1L,"Budapest", 1038));
            add(new CityDto(2L,"Budapest", 1111));
            add(new CityDto(3L, "Budapest", 1122));
            add(new CityDto(4L, "Miskolc", 3500));
            add(new CityDto(5L, "Szentendre", 2000));
        }};
        ResponseEntity<City[]> responseEntity = restTemplate.getForEntity(BASE_URL, City[].class);
        List<City> actual = Arrays.asList(responseEntity.getBody());
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testGetCityById() {
        CityDto expected = new CityDto(1L,"Budapest", 1038);
        ResponseEntity<CityDto> responseEntity = restTemplate.getForEntity(BASE_URL + "/1", CityDto.class);
        CityDto actual = responseEntity.getBody();

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void addNewCity_shouldReturnSameCity() {
        CityDto city = new CityDto(null, "Város", 1135);
        restTemplate.postForObject(BASE_URL, city, CityDto.class);
        CityDto result = restTemplate.getForObject(BASE_URL + "/6", CityDto.class);

        assertEquals(result.getName(), city.getName());
    }

    @Test
    public void updateCity_withOnePostedCity_returnsUpdateCity() {
        CityDto city = new CityDto(null, "Város", 1135);
        restTemplate.postForObject(BASE_URL, city, CityDto.class);

        city.setName("Updated");
        restTemplate.put(BASE_URL + "/6", city);

        CityDto updatedCity = restTemplate.getForObject(BASE_URL + "/6", CityDto.class);

        assertEquals("Updated", updatedCity.getName());
    }

    @Test
    public void deleteCityById_withSomePostedCity_getAllShouldReturnRemainingCities() {
        Set<CityDto> testCities =
                Stream.of(restTemplate.getForObject(BASE_URL, CityDto[].class)).collect(Collectors.toSet());
        CityDto removedCity = testCities.stream().filter(city -> city.getId() == 1L).findFirst().get();
        restTemplate.delete(BASE_URL + "/1");
        testCities.remove(removedCity);

        Set<CityDto> remainingCities =
                Stream.of(restTemplate.getForObject(BASE_URL, CityDto[].class)).collect(Collectors.toSet());

        assertEquals(testCities.size(), remainingCities.size());
    }
}
