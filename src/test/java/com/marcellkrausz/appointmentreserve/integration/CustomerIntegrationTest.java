package com.marcellkrausz.appointmentreserve.integration;

import com.marcellkrausz.appointmentreserve.model.Customer;
import com.marcellkrausz.appointmentreserve.model.dto.CustomerDto;
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
public class CustomerIntegrationTest {

    @LocalServerPort
    private Integer port;

    private String BASE_URL;

    private static TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeEach
    public void init() {
        BASE_URL = "http://localhost:" + port + "/customer";
    }

    @Test
    public void testAllFromCustomer() {
        List<CustomerDto> excepted = new ArrayList<>() {{
            add(new CustomerDto(1L,"János", "Bíró", "06706113333", "birojanos@gmail.com",1L));
            add(new CustomerDto(2L,"Zsolt", "Zemen","06709823366","zemenzsolt@gmail.com",2L));
        }};
        ResponseEntity<Customer[]> responseEntity = restTemplate.getForEntity(BASE_URL, Customer[].class);
        List<Customer> actual = Arrays.asList(responseEntity.getBody());
        assertEquals(excepted.size(), actual.size());
    }

    @Test
    public void testGetCustomerById() {
        CustomerDto expected = new CustomerDto(2L,"Zsolt", "Zemen", "06709823366","zemenzsolt@gmail.com", 2L);
        ResponseEntity<CustomerDto> responseEntity = restTemplate.getForEntity(BASE_URL + "/2", CustomerDto.class);
        CustomerDto actual = responseEntity.getBody();

        assertEquals(expected.getFirstName(), actual.getFirstName());
    }

    @Test
    public void addNewCustomer_ShouldReturnSameCustomer() {
        CustomerDto customer = new CustomerDto(null,"Marcell", "Krausz", "010101","marcellk@gmail.com", 2L);
        restTemplate.postForObject(BASE_URL, customer, CustomerDto.class);
        CustomerDto result = restTemplate.getForObject(BASE_URL + "/3", CustomerDto.class);

        assertEquals(result.getFirstName(), customer.getFirstName());
    }

    @Test
    public void updateCustomer_withOnePostedCustomer_returnsUpdateCustomer() {
        CustomerDto customer = new CustomerDto(null,"Marcell", "Krausz", "010101","marcellk@gmail.com", 2L);
        restTemplate.postForObject(BASE_URL, customer, CustomerDto.class);

        customer.setFirstName("Updated");
        restTemplate.put(BASE_URL + "/3", customer);

        CustomerDto updatedCustomer = restTemplate.getForObject(BASE_URL + "/3", CustomerDto.class);

        assertEquals("Updated", updatedCustomer.getFirstName());
    }

    @Test
    public void deleteCustomerById_withSomePostedCustomer_getAllShouldReturnRemainingCustomers() {
        Set<CustomerDto> testCustomers =
                Stream.of(restTemplate.getForObject(BASE_URL, CustomerDto[].class)).collect(Collectors.toSet());
        CustomerDto removedCustomer = testCustomers.stream().filter(customer -> customer.getId() == 1L).findFirst().get();
        restTemplate.delete(BASE_URL + "/1");
        testCustomers.remove(removedCustomer);

        Set<CustomerDto> remainingCustomer =
                Stream.of(restTemplate.getForObject(BASE_URL, CustomerDto[].class)).collect(Collectors.toSet());
        assertEquals(testCustomers.size(), remainingCustomer.size());
    }
}
