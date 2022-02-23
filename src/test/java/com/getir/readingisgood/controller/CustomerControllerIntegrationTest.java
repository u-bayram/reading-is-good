package com.getir.readingisgood.controller;

import com.getir.readingisgood.ReadingisgoodApplication;
import com.getir.readingisgood.dto.NewCustomerRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ReadingisgoodApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testNewCustomer() {
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/customer/", NewCustomerRequestDto.builder()
                        .email("test" + UUID.randomUUID() + "@test.com").name("name").surname("name").build(), String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }


}
