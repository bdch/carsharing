package com.carsharing.carsharing.Mapper;

import com.carsharing.carsharing.dto.BookingDTO;
import com.carsharing.carsharing.model.Booking;
import com.carsharing.carsharing.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

// MapStruct-Annotation: Diese Klasse wird als Mapper von MapStruct verwendet.
// MapStruct ist eine Bibliothek, die automatisch Implementierungen für diese Schnittstelle generiert.
@Mapper(componentModel = "spring")
public interface BookingMapper {

    // Singleton-Instanz des Mappers: Diese wird intern von MapStruct erstellt.
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    // **Methode 1: Entität zu DTO konvertieren**
    // konvertiert ein `Booking`-Objekt in ein `BookingDTO`. Diese Methode wird automatisch
    // von MapStruct implementiert. Die Annotation @Mapping gibt an, wie Felder gemappt werden.
    @Mapping(target = "id", source = "id") // Das `id`-Feld im DTO kommt direkt vom `id` der Entität.
    @Mapping(target = "carId", source = "car.id") // Das `carId` im DTO wird aus `car.id` der Entität extrahiert.
    BookingDTO bookingToBookingDTO(Booking booking);

    // **Methode 2: DTO zu Entität konvertieren**
    // diese Methode konvertiert ein `BookingDTO` zurück in ein `Booking`-Objekt.
    @Mapping(target = "car", source = "carId") // Das `car`-Objekt in der Entität wird aus `carId` des DTO erstellt.
    @Mapping(target = "customerName", source = "customerName") // Kunde wird 1:1 übertragen.
    @Mapping(target = "startTime", source = "startTime") // Startzeit wird 1:1 übertragen.
    @Mapping(target = "endTime", source = "endTime") // Endzeit wird 1:1 übertragen.
    Booking bookingDTOToBooking(BookingDTO bookingDTO);

    // **Hilfsmethode: carId zu einem Car-Objekt konvertieren**
    // MapStruct kann mit Feldern umgehen, benötigt aber eine Methode, um eine `carId`
    // (Long) in ein `Car`-Objekt umzuwandeln. Hier wird ein neues `Car`-Objekt erstellt
    // und die ID gesetzt.
    default Car map(Long carId) {
        if (carId == null) {
            return null;
        }
        Car car = new Car();
        car.setId(carId);
        return car;
    }

}
