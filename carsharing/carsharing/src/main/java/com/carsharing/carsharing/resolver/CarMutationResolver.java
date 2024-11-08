package com.carsharing.carsharing.resolver;

import com.carsharing.carsharing.dto.CarDTO;
import com.carsharing.carsharing.service.CarService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class CarMutationResolver implements GraphQLMutationResolver {

    private final CarService carService;

    public CarMutationResolver(CarService carService) {
        this.carService = carService;
    }

    public CarDTO addCar(CarDTO carDTO) {
        return carService.saveCarDTO(carDTO);
    }

    public Boolean deleteCar(Long id) {
        carService.deleteCarDTO(id);
        return true;
    }

}
