package com.carsharing.carsharing.service;

import com.carsharing.carsharing.Mapper.CarMapper;
import com.carsharing.carsharing.model.Car;
import com.carsharing.carsharing.repository.BookingRepository;
import com.carsharing.carsharing.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.carsharing.carsharing.dto.CarDTO;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

    @Service
    public class CarService {

        @Autowired
        private final CarRepository carRepository;

        @Autowired
        private final BookingRepository bookingRepository;

        // injection via constructor
        public CarService(CarRepository carRepository, BookingRepository bookingRepository) {
            this.carRepository = carRepository;
            this.bookingRepository = bookingRepository;
        }

        public List<Car> getAllCars() {
            return carRepository.findAll();
        }

        public Car getCarById(Long id) {
            return carRepository.findById(id).orElse(null);
        }

        public Car saveCar(Car car) {
            return carRepository.save(car);
        }

        public void deleteCar(Long id) {
            Optional<Car> optionalCar = carRepository.findById(id);
            if (optionalCar.isPresent()) {
                carRepository.delete(optionalCar.get());
                System.out.println("Car deleted: " + optionalCar.get());
            } else {
                throw new EntityNotFoundException("Car not found");
            }
        }

        public Car addCar(Car car) {return carRepository.save(car);}

        public List<CarDTO> getAllCarsDTO() {
            List<Car> cars = carRepository.findAll();
            return cars.stream()
                    .map(CarMapper.INSTANCE::toDTO) // Entity -> DTO
                    .collect(Collectors.toList());


        }

        public CarDTO  getCarByIdDTO(Long id) {
            Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
            return CarMapper.INSTANCE.toDTO(car);
        }

        public CarDTO saveCarDTO(CarDTO carDTO) {
            Car car = CarMapper.INSTANCE.toEntity(carDTO); // DTO -> Entity
            Car savedCar = carRepository.save(car);
            return CarMapper.INSTANCE.toDTO(savedCar); // Entity -> DTO
        }

        public void deleteCarDTO(Long id) {
            carRepository.deleteById(id);
        }



    }
