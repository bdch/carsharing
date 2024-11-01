package com.carsharing.carsharing.Mapper;

import com.carsharing.carsharing.dto.BookingDTO;
import com.carsharing.carsharing.model.Booking;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-31T15:06:22+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class BookingMapperImpl implements BookingMapper {

    @Override
    public Booking toEntity(BookingDTO bookingDTO) {
        if ( bookingDTO == null ) {
            return null;
        }

        Booking booking = new Booking();

        return booking;
    }

    @Override
    public BookingDTO toDTO(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingDTO bookingDTO = new BookingDTO();

        return bookingDTO;
    }
}
