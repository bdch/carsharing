package com.carsharing.carsharing;

import com.carsharing.carsharing.dto.CarDTO;
import com.carsharing.carsharing.model.Car;
import com.carsharing.carsharing.repository.CarRepository;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class CarTestsContainer {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CarRepository carRepository;

    @Container
    static MySQLContainer<?> databaseContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("user")
            .withPassword("password");

    @DynamicPropertySource
    static void configureDatabase(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", databaseContainer::getJdbcUrl);
        registry.add("spring.datasource.username", databaseContainer::getUsername);
        registry.add("spring.datasource.password", databaseContainer::getPassword);
    }

    @BeforeEach
    public void setUp() {
        carRepository.deleteAll();
    }

    @Test
    void testGetAllCars() {
        // Datenbank vorbereiten
        CarDTO car1 = new CarDTO(null, "Audi", "A4", "M-AB 1234");
        CarDTO car2 = new CarDTO(null, "BMW", "X5", "M-CD 5678");

        testRestTemplate.postForEntity("/api/v1/cars", car1, CarDTO.class);
        testRestTemplate.postForEntity("/api/v1/cars", car2, CarDTO.class);

        // GET-Anfrage
        ResponseEntity<List<CarDTO>> response = testRestTemplate.exchange(
                "/api/v1/cars",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }


    @Test
    void testGetCarById() {
        // Auto hinzufügen
        CarDTO car = new CarDTO(null, "Mercedes", "C-Class", "M-EF 9101");
        ResponseEntity<CarDTO> postResponse = testRestTemplate.postForEntity("/api/v1/cars", car, CarDTO.class);
        Long carId = postResponse.getBody().getId();

        // GET-Anfrage für die spezifische ID
        ResponseEntity<CarDTO> response = testRestTemplate.getForEntity("/api/v1/cars/" + carId, CarDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Mercedes", response.getBody().getBrand());
    }


    @Test
    void testAddCar() {
        // Neues Auto hinzufügen
        CarDTO newCar = new CarDTO(null, "Volkswagen", "Golf", "M-GH 5678");
        ResponseEntity<CarDTO> response = testRestTemplate.postForEntity("/api/v1/cars", newCar, CarDTO.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("Volkswagen", response.getBody().getBrand());
    }

    @Test
    void testUpdateCar() {
        // Auto hinzufügen
        CarDTO car = new CarDTO(null, "Audi", "A3", "M-XY 1234");
        ResponseEntity<CarDTO> postResponse = testRestTemplate.postForEntity("/api/v1/cars", car, CarDTO.class);
        Long carId = postResponse.getBody().getId();

        // Auto aktualisieren
        CarDTO updatedCar = new CarDTO(carId, "Audi", "A4", "M-XY 5678");
        ResponseEntity<CarDTO> putResponse = testRestTemplate.exchange(
                "/api/v1/cars/" + carId,
                HttpMethod.PUT,
                new HttpEntity<>(updatedCar),
                CarDTO.class
        );

        assertEquals(HttpStatus.OK, putResponse.getStatusCode());
        assertNotNull(putResponse.getBody());
        assertEquals("A4", putResponse.getBody().getModel());
        assertEquals("M-XY 5678", putResponse.getBody().getLicensePlate());
    }

}
