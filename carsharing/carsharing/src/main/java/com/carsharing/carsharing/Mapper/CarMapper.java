package com.carsharing.carsharing.Mapper;

import com.carsharing.carsharing.dto.CarDTO;
import com.carsharing.carsharing.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    //DTO -> Entity
    @Mapping(target = "id" , ignore = true)
    Car toEntity(CarDTO carDTO);

    //Entity -> DTO
    CarDTO toDTO(Car car);


}
