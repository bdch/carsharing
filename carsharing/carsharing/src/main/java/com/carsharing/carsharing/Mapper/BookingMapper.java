package com.carsharing.carsharing.Mapper;

import com.carsharing.carsharing.dto.BookingDTO;
import com.carsharing.carsharing.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    //DTO -> Entity
    @Mapping(target = "id" , ignore = true)
    Booking toEntity(BookingDTO bookingDTO);

    //Entity -> DTO
    BookingDTO toDTO(Booking booking);
}
