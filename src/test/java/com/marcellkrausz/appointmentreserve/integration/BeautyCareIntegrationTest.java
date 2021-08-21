package com.marcellkrausz.appointmentreserve.integration;

import com.marcellkrausz.appointmentreserve.model.BeautyCare;
import com.marcellkrausz.appointmentreserve.model.dto.BeautyCareDto;
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
public class BeautyCareIntegrationTest {

    @LocalServerPort
    private Integer port;

    private String BASE_URL;

    private static TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeEach
    public void init() {
        BASE_URL = "http://localhost:" + port + "/beautycare";
    }

    @Test
    public void testAllFromBeautyCare() {
        List<BeautyCareDto> expected = new ArrayList<>() {{
            add(new BeautyCareDto(1L, "Nyomásterápia", 3000, 30));
            add(new BeautyCareDto(2L, "Mezopen", 15000, 60));
            add(new BeautyCareDto(3L, "Mezoterápiás testkezelés", 15000, 45));
            add(new BeautyCareDto(4L, "Derma Clear kezelés problémás bőrre", 11000,30));
            add(new BeautyCareDto(5L,"Recorvery növényi őssejtes kezelés", 12000,15));
        }};

        ResponseEntity<BeautyCare[]> responseEntity = restTemplate.getForEntity(BASE_URL, BeautyCare[].class);
        List<BeautyCare> actual = Arrays.asList(responseEntity.getBody());
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void testGetBeautyCareById() {
        BeautyCareDto expected = new BeautyCareDto(2L, "Mezopen", 15000,60);
        ResponseEntity<BeautyCareDto> responseEntity = restTemplate.getForEntity(BASE_URL + "/2", BeautyCareDto.class);
        BeautyCareDto actual = responseEntity.getBody();

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void addNewBeautyCare_shouldReturnSameBeautyCare() {
        BeautyCareDto beautyCareDto = new BeautyCareDto(null, "test4", 12000, 30);
        restTemplate.postForObject(BASE_URL, beautyCareDto, BeautyCareDto.class);
        BeautyCareDto result = restTemplate.getForObject(BASE_URL + "/6", BeautyCareDto.class);

        assertEquals(result.getName(), beautyCareDto.getName());
    }

    @Test
    public void updateBeautyCare_shouldReturnSameBeautyCare() {
        BeautyCareDto beautyCareDto = new BeautyCareDto(null, "test4", 12000, 30);
        restTemplate.postForObject(BASE_URL, beautyCareDto, BeautyCareDto.class);

        beautyCareDto.setName("Updated");
        restTemplate.put(BASE_URL + "/6", beautyCareDto);

        BeautyCareDto updatedCity = restTemplate.getForObject(BASE_URL + "/6", BeautyCareDto.class);

        assertEquals("Updated", updatedCity.getName());
    }

    @Test
    public void deleteBeautyCareById_withSomePostedBeautyCare_getAllShouldReturnRemainingBeautyCares() {
        Set<BeautyCareDto> testBeautyCares =
                Stream.of(restTemplate.getForObject(BASE_URL, BeautyCareDto[].class)).collect(Collectors.toSet());
        BeautyCareDto removedBeautyCare = testBeautyCares.stream().filter(beautyCare -> beautyCare.getId() == 1L).findFirst().get();
        restTemplate.delete(BASE_URL + "/1");
        testBeautyCares.remove(removedBeautyCare);

        Set<BeautyCareDto> remainingBeautyCares =
                Stream.of(restTemplate.getForObject(BASE_URL, BeautyCareDto[].class)).collect(Collectors.toSet());

        assertEquals(testBeautyCares.size(), remainingBeautyCares.size());
    }
}
