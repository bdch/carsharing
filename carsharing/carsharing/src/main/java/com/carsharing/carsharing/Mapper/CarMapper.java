package com.carsharing.carsharing.Mapper;

import com.carsharing.carsharing.dto.CarDTO;
import com.carsharing.carsharing.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {

   CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);


   CarDTO carToCarDTO(Car car);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "model", source = "model")
    @Mapping(target = "licensePlate", source = "licensePlate")
   Car carDTOToCar(CarDTO carDTO);
}
