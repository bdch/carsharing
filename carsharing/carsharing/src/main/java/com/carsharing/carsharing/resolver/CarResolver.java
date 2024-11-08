package com.carsharing.carsharing.resolver;

import com.carsharing.carsharing.dto.CarDTO;
import com.carsharing.carsharing.service.CarService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarResolver implements GraphQLQueryResolver {

    private final CarService carService;

    public CarResolver(CarService carService) {
        this.carService = carService;
    }

    public List<CarDTO> allCars() {
        return carService.getAllCarsDTO();
    }

    public CarDTO carById(Long id) {
        return carService.getCarByIdDTO(id);
    }

    public CarDTO addCar(CarDTO carDTO) {
        return carService.saveCarDTO(carDTO);
    }

    public Boolean deleteCar(Long id) {
        carService.deleteCarDTO(id);
        return true;
    }

}