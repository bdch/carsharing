package com.carsharing.carsharing.Mapper;

import com.carsharing.carsharing.dto.BookingDTO;
import com.carsharing.carsharing.model.Booking;
import com.carsharing.carsharing.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    // Entität zu DTO
    @Mapping(target = "id", source = "id") // Jetzt wird die ID korrekt gemappt
    @Mapping(target = "carId", source = "car.id") // carId kommt aus car.id im Booking-Objekt
    BookingDTO bookingToBookingDTO(Booking booking);

    // DTO zu Entität
    @Mapping(target = "car", source = "carId") // car kommt von carId im DTO
    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "startTime", source = "startTime")
    @Mapping(target = "endTime", source = "endTime")
    Booking bookingDTOToBooking(BookingDTO bookingDTO);

    // Hilfsmethode, um die carId in ein Car-Objekt zu konvertieren
    default Car map(Long carId) {
        if (carId == null) {
            return null;
        }
        Car car = new Car();
        car.setId(carId);
        return car;
    }

}
