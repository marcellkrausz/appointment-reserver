package com.marcellkrausz.appointmentreserve.integration;

import com.marcellkrausz.appointmentreserve.model.Appointment;
import com.marcellkrausz.appointmentreserve.model.dto.AppointmentDto;
import com.marcellkrausz.appointmentreserve.model.dto.BeautyCareDto;
import com.marcellkrausz.appointmentreserve.model.dto.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppointmentIntegrationTest {

    @LocalServerPort
    private Integer port;

    private String BASE_URL;

    private static TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeEach
    public void init() {
        BASE_URL = "http://localhost:" + port + "/appointment";
    }

    @Test
    public void testAllFromAppointment() {
        ResponseEntity<Appointment[]> responseEntity = restTemplate.getForEntity(BASE_URL, Appointment[].class);
        List<Appointment> actual = Arrays.asList(responseEntity.getBody());

        assertEquals(4, actual.size());
    }

    @Test
    public void addNewAppointment_shouldReturnSameAppointment() {
        CustomerDto customerDto = new CustomerDto(2L, "Zsolt", "Zemen", "06709823366", "zemenzsolt@gmail.com", 2L);
        BeautyCareDto beautyCareDto = new BeautyCareDto(2L, "Mezopen", 15000, 60);
        AppointmentDto appointmentDto = new AppointmentDto(null
                , LocalDateTime.of(2021, 12, 18, 10, 00, 00)
                , LocalDateTime.of(2021, 12, 18, 14, 00, 00)
                , customerDto
                , Set.of(beautyCareDto));

        restTemplate.postForObject(BASE_URL, appointmentDto, AppointmentDto.class);
        AppointmentDto result = restTemplate.getForObject(BASE_URL + "/5", AppointmentDto.class);

        assertEquals(result.getAppointmentDateStart(), appointmentDto.getAppointmentDateStart());
    }

    @Test
    public void updateAppointment_withOnePostedAppointment_ReturnUpdateAppointment() {
        CustomerDto customerDto = new CustomerDto(2L,
                "Zsolt",
                "Zemen",
                "06709823366",
                "zemenzsolt@gmail.com",
                2L);
        BeautyCareDto beautyCareDto = new BeautyCareDto(2L, "Mezopen", 15000, 60);
        AppointmentDto appointmentDto = new AppointmentDto(null
                , LocalDateTime.of(2021, 12, 18, 10, 00, 00)
                , LocalDateTime.of(2021, 12, 18, 14, 00, 00)
                , customerDto
                , Set.of(beautyCareDto));
        restTemplate.postForObject(BASE_URL, appointmentDto, AppointmentDto.class);

        appointmentDto.setAppointmentDateStart(
                LocalDateTime.of(2021, 12, 18, 9, 00, 00));
        restTemplate.put(BASE_URL + "/5", appointmentDto);

        AppointmentDto updatedAppointment = restTemplate.getForObject(BASE_URL + "/5", AppointmentDto.class);

        assertEquals(LocalDateTime.of(2021, 12, 18, 9, 00, 00),
                updatedAppointment.getAppointmentDateStart());
    }

    @Test
    public void deleteAppointmentById_withSomePostedAppointment_getAllShouldReturnRemainingAppointments() {
        Set<AppointmentDto> testAppointments =
                Stream.of(restTemplate.getForObject(BASE_URL, AppointmentDto[].class)).collect(Collectors.toSet());
        AppointmentDto removedAppointment = testAppointments
                .stream()
                .filter(appointment -> appointment.getId() == 1L)
                .findFirst()
                .get();

        restTemplate.delete(BASE_URL + "/1");
        testAppointments.remove(removedAppointment);

        Set<AppointmentDto> remainingAppointments =
                Stream.of(restTemplate.getForObject(BASE_URL, AppointmentDto[].class)).collect(Collectors.toSet());

        assertEquals(testAppointments.size(), remainingAppointments.size());
    }
}
