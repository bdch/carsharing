package com.carsharing.carsharing.controller;

import com.carsharing.carsharing.dto.CarDTO;
import com.carsharing.carsharing.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.carsharing.carsharing.model.Car;
import com.carsharing.carsharing.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController{

    private final CarService carService;

    // Konstruktorinjektion
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    //Get all cars
    @GetMapping
    public List<CarDTO> getAllCars() {
        return carService.getAllCarsDTO();
    }

    // Auto nach ID als DTO zurückgeben
    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarByIdDTO(id));
    }

    // Neues Auto anlegen
    @PostMapping
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) {
        CarDTO createdCarDTO = carService.saveCarDTO(carDTO);
        return ResponseEntity.status(201).body(createdCarDTO);
    }

    // Auto aktualisieren
    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody CarDTO carDetails) {
        CarDTO updatedCarDTO = carService.saveCarDTO(carDetails);
        return ResponseEntity.ok(updatedCarDTO);
    }

    // Auto löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCarDTO(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
