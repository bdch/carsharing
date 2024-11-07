package com.carsharing.carsharing.controller;

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


    @Autowired
    public CarService carService;

    //Get all cars
    @GetMapping
    public List<Car> getAllCars(){
        List<Car> cars = carService.getAllCars();
        System.out.println("Cars: " + cars); // Logging der Antwort
        return cars;
    }

    //Get car by id
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        Car car = carService.getCarById(id);
        if (car == null) {
            return ResponseEntity.notFound().build(); // 404 if car not found
        }
        return ResponseEntity.ok(car);
    }

    //add a new car
    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car savedCar = carService.addCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    //update a car id
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails){
        Car car = carService.getCarById(id);
        if(car != null) {
            car.setBrand(carDetails.getBrand());
            car.setModel(carDetails.getModel());
            car.setLicensePlate(carDetails.getLicensePlate());

            // Speichern und Rückgabe des aktualisierten Autos
            Car updatedCar = carService.saveCar(car);
            return ResponseEntity.ok(updatedCar); // Gibt 200 OK zurück
        }
        return ResponseEntity.notFound().build(); // Gibt 404 Not Found zurück
    }

    //delete a car by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id){
        if(carService.getCarById(id) == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        carService.deleteCar(id);
        return ResponseEntity.noContent().build(); // 204 after successful deletion
    }



}
