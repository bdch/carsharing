package com.carsharing.carsharing.Mapper;

import com.carsharing.carsharing.dto.CarDTO;
import com.carsharing.carsharing.model.Car;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-04T13:07:58+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class CarMapperImpl implements CarMapper {

    @Override
    public Car toEntity(CarDTO carDTO) {
        if ( carDTO == null ) {
            return null;
        }

        Car car = new Car();

        return car;
    }

    @Override
    public CarDTO toDTO(Car car) {
        if ( car == null ) {
            return null;
        }

        CarDTO carDTO = new CarDTO();

        return carDTO;
    }
}
