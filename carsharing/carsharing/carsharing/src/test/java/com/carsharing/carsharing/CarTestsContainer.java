package com.carsharing.carsharing;

import com.carsharing.carsharing.model.Car;
import com.carsharing.carsharing.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarTestsContainer {

    @Autowired
    private CarRepository carRepository;

    private static Long savedCarId1;
    private static Long savedCarId2;
    private static Long savedCarId3;

    // PostgreSQL Testcontainer-Definition
    @Container
    static PostgreSQLContainer<?> carDatabaseContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("car_testdb")
            .withUsername("user")
            .withPassword("password");

    @DynamicPropertySource
    static void configureDatabase(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", carDatabaseContainer::getJdbcUrl);
        registry.add("spring.datasource.username", carDatabaseContainer::getUsername);
        registry.add("spring.datasource.password", carDatabaseContainer::getPassword);
    }

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @BeforeEach
    public void setUp() {

        Car auto1 = new Car("Audi", "A4", "M-AB 1234");
        Car auto2 = new Car("BMW", "X5", "M-CD 5678");
        Car auto3 = new Car("Mercedes", "C-Klasse", "M-EF 9101");

        auto1 = carRepository.save(auto1);
        auto2 = carRepository.save(auto2);
        auto3 = carRepository.save(auto3);

        savedCarId1 = auto1.getId();
        savedCarId2 = auto2.getId();
        savedCarId3 = auto3.getId();
    }

    @Test
    void testAddCarWithInvalidData() {
        // Erstellen eines neuen Autos ohne erforderliche Felder
        Car invalidCar = new Car(null, null, null);

        // Führe die POST-Anfrage aus
        ResponseEntity<Car> response = testRestTemplate.postForEntity("/api/v1/cars", invalidCar, Car.class);

        // Überprüfen, ob der Statuscode 400 BAD REQUEST ist
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode()); //FIXME: OUTPUT: 200 OK
    }

    @Test
    void testGetCars() {
// Führe die GET-Anfrage aus
        ResponseEntity<List<Car>> response = testRestTemplate.exchange("/api/v1/cars", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        // Überprüfen, ob der Statuscode 200 OK ist
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Sicherstellen, dass die Antwort nicht null ist und mindestens ein Auto enthält
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isGreaterThan(0);
    }

    @Test
    void testGetCarById() {
        ResponseEntity<Car> response = testRestTemplate.getForEntity("/api/v1/cars/" + savedCarId1, Car.class);

        // Überprüfen, ob der Statuscode 200 OK ist
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Sicherstellen, dass die Antwort nicht null ist
        assertThat(response.getBody()).isNotNull();

        // Überprüfen, ob die ID der Antwort der gespeicherten ID entspricht
        assertEquals(savedCarId1, response.getBody().getId());

        // Überprüfen, ob die Rückgabe die erwarteten Werte enthält
        assertEquals("Audi", response.getBody().getBrand());
        assertEquals("A4", response.getBody().getModel());
        assertEquals("M-AB 1234", response.getBody().getLicensePlate());
    }

    @Test
    void testAddCar() throws InterruptedException {

        // Erstellen eines neuen Autos
        Car newCar = new Car("Volkswagen", "Golf", "M-GH 1234");

        // Führe die POST-Anfrage aus
        ResponseEntity<Car> response = testRestTemplate.postForEntity("/api/v1/cars", newCar, Car.class);

        // Überprüfen, ob der Statuscode 201 CREATED ist
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Sicherstellen, dass die Antwort nicht null ist
        assertThat(response.getBody()).isNotNull();

        // Überprüfen, ob das Auto die erwarteten Werte enthält
        assertEquals("Volkswagen", response.getBody().getBrand());
        assertEquals("Golf", response.getBody().getModel());
        assertEquals("M-GH 1234", response.getBody().getLicensePlate());

        // Überprüfen, ob das Auto in der Datenbank gespeichert wurde
        Long savedCarId = response.getBody().getId();
        assertTrue(carRepository.findById(savedCarId).isPresent());
    }

    @Test
    void testUpdateCar() {
        // Neuen Car anlegen, um ihn zu speichern
        Car carToUpdate = new Car("Audi", "A4", "M-AB 1234");
        Car savedCar = carRepository.save(carToUpdate); // Zuerst speichern

        // Neue Werte für das Auto
        Car updatedCarDetails = new Car("Audi", "A6", "M-AB 4321");

        // Führe die PUT-Anfrage aus
        ResponseEntity<Car> response = testRestTemplate.exchange("/api/v1/cars/" + savedCar.getId(), HttpMethod.PUT, new HttpEntity<>(updatedCarDetails), Car.class);

        // Überprüfen, ob der Statuscode 200 OK ist
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals(updatedCarDetails.getBrand(), response.getBody().getBrand());
        assertEquals(updatedCarDetails.getModel(), response.getBody().getModel());
        assertEquals(updatedCarDetails.getLicensePlate(), response.getBody().getLicensePlate());
    }

    @Test
    void testDeleteCar() {

        // Zuerst ein Auto hinzufügen, damit wir eines löschen können
        Car carToDelete = new Car("Audi", "A4", "M-AB 1234");
        carToDelete = carRepository.save(carToDelete); // Speichere das Auto und hole die ID

        Long carIdToDelete = carToDelete.getId(); // Hol die ID des gespeicherten Autos

        // Führe die DELETE-Anfrage aus
        ResponseEntity<Void> deleteResponse = testRestTemplate.exchange("/api/v1/cars/" + carIdToDelete, HttpMethod.DELETE, null, Void.class);

        // Überprüfen, ob der Statuscode 204 No Content ist
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

        ResponseEntity<Car> checkResponse = testRestTemplate.exchange("/api/v1/cars/" + carIdToDelete, HttpMethod.GET,null, Car.class);

        // Protokolliere die Statusantwort
        System.out.println("Check response status: " + checkResponse.getStatusCode());

        assertEquals(HttpStatus.NOT_FOUND, checkResponse.getStatusCode());
    }
}
