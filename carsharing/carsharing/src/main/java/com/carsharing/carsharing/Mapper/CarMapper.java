package com.carsharing.carsharing.Mapper;

import com.carsharing.carsharing.dto.CarDTO;
import com.carsharing.carsharing.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

// MapStruct-Annotation: Markiert diese Schnittstelle als Mapper, der von MapStruct automatisch implementiert wird.
@Mapper(componentModel = "spring")
public interface CarMapper {

    // Singleton-Instanz des Mappers: Wird von MapStruct automatisch bereitgestellt.
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);


    // **Methode 1: Entität zu DTO konvertieren**
    // diese Methode wandelt ein `Car`-Objekt (Datenbankmodell) in ein `CarDTO`-Objekt (DTO) um.
   CarDTO carToCarDTO(Car car);

    // **Methode 2: DTO zu Entität konvertieren**
    // diese Methode wandelt ein `CarDTO`-Objekt in ein `Car`-Objekt um. Die @Mapping-Annotationen
    // stellen sicher, dass die Felder korrekt gemappt werden.
    @Mapping(target = "id", source = "id")
    @Mapping(target = "brand", source = "brand")
    @Mapping(target = "model", source = "model")
    @Mapping(target = "licensePlate", source = "licensePlate")
   Car carDTOToCar(CarDTO carDTO);
}
