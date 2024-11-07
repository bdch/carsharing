package com.carsharing.carsharing.service;

import com.carsharing.carsharing.Mapper.CarMapper;
import com.carsharing.carsharing.dto.CarDTO;
import com.carsharing.carsharing.model.Car;
import com.carsharing.carsharing.repository.BookingRepository;
import com.carsharing.carsharing.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

    @Service
    public class CarService {


        private final CarRepository carRepository;
        private final BookingRepository bookingRepository;

        @Autowired
        // injection via constructor
        public CarService(CarRepository carRepository, BookingRepository bookingRepository) {
            this.carRepository = carRepository;
            this.bookingRepository = bookingRepository;
        }

        // Rückgabe einer Liste von CarDTOs
        public List<CarDTO> getAllCarsDTO() {
            List<Car> cars = carRepository.findAll();
            return cars.stream()
                    .map(CarMapper.INSTANCE::carToCarDTO) // Car -> CarDTO
                    .collect(Collectors.toList());
        }

        // Rückgabe eines einzelnen CarDTOs anhand der ID
        public CarDTO getCarByIdDTO(Long id) {
            Car car = carRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));
            return CarMapper.INSTANCE.carToCarDTO(car);
        }

        // Speichern eines CarDTOs und Rückgabe als CarDTO
        public CarDTO saveCarDTO(CarDTO carDTO) {
            Car car = CarMapper.INSTANCE.carDTOToCar(carDTO);
            Car savedCar = carRepository.save(car);
            return CarMapper.INSTANCE.carToCarDTO(savedCar);
        }

        // Löschen eines Cars durch ID
        public void deleteCarDTO(Long id) {
            if (!carRepository.existsById(id)) {
                throw new ResourceNotFoundException("Car not found with id: " + id);
            }
            carRepository.deleteById(id);
        }

        public Car addCar(Car car) {return carRepository.save(car);}

    }
