package com.carsharing.carsharing;

import com.carsharing.carsharing.model.Booking;
import com.carsharing.carsharing.model.Car;
import com.carsharing.carsharing.repository.BookingRepository;
import com.carsharing.carsharing.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookingTestsContainer {

    @Autowired
    private BookingRepository bookingRepository;

    private static long bookingId1;
    private static long bookingId2;
    private static long bookingId3;

    // PostgreSQL Testcontainer-Definition
    @Container
    static PostgreSQLContainer<?> bookingDatabaseContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("booking_testdb")
            .withUsername("user")
            .withPassword("password");

    @DynamicPropertySource
    static void configureDatabase(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", bookingDatabaseContainer::getJdbcUrl);
        registry.add("spring.datasource.username", bookingDatabaseContainer::getUsername);
        registry.add("spring.datasource.password", bookingDatabaseContainer::getPassword);
    }

    @LocalServerPort
    private int port;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {

        Car auto1 = new Car("Audi", "A4", "M-AB 1234");
        Car auto2 = new Car("BMW", "X5", "M-CD 5678");
        Car auto3 = new Car("Mercedes", "C-Klasse", "M-EF 9101");

        auto1 = carRepository.save(auto1); // Speichern des Autos
        auto2 = carRepository.save(auto2); // Speichern des Autos
        auto3 = carRepository.save(auto3); // Speichern des Autos

        Booking booking1 = new Booking(auto1, "2021-01-01", "2021-01-02", "Max Mustermann");
        Booking booking2 = new Booking(auto2, "2020-02-01", "2021-02-02", "Florian Fröhlich");
        Booking booking3 = new Booking(auto3, "2020-03-01", "2024-03-02", "Lukas Peinze");

        booking1 = bookingRepository.save(booking1);
        booking2 = bookingRepository.save(booking2);
        booking3 = bookingRepository.save(booking3);

        bookingId1 = booking1.getId();
        bookingId2 = booking2.getId();
        bookingId3 = booking3.getId();
    }

    @Test
    public void testDeserializeBooking() throws IOException {
        String json = "{\"id\":1,\"car\":{\"id\":1,\"brand\":\"Audi\",\"model\":\"A4\",\"licensePlate\":\"M-AB 1234\"},\"startTime\":\"2021-01-01\",\"endTime\":\"2021-01-02\",\"customerName\":\"Max Mustermann\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        Booking booking = objectMapper.readValue(json, Booking.class);

        assertNotNull(booking);
        assertEquals("Max Mustermann", booking.getCustomerName());
        assertNotNull(booking.getCar());
    }

    @Test
    void testGetAllBookings() {

        ResponseEntity<List<Booking>> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/api/v1/bookings",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Booking>>() {
                });

        // Überprüfen, ob der Statuscode 200 OK ist
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Booking> bookings = response.getBody();

        assertNotNull(bookings);
        assertThat(response.getBody().size()).isGreaterThan(0);
        assertEquals(3, bookings.size());
    }

}
