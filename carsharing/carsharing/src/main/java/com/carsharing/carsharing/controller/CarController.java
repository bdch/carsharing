package com.carsharing.carsharing.controller;

import com.carsharing.carsharing.repository.CarRepository;
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
        return carService.getAllCars();
    }

    //Get car by id
    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id){
        return carService.getCarById(id);
    }

    //add a new car
    @PostMapping
    public Car addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    //update a car id
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car carDetails){
        Car car = carService.getCarById(id);
        if(car != null) {
            car.setBrand(carDetails.getBrand());
            car.setModel(carDetails.getModel());
            car.setLicensePlate(carDetails.getLicensePlate());
            return carService.saveCar(car);
        }
        throw new NullPointerException("Car not found");
    }

    //delete a car by id
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
    }
}
