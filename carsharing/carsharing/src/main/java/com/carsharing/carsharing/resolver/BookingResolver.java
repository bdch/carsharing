package com.carsharing.carsharing.resolver;

import com.carsharing.carsharing.dto.BookingDTO;
import com.carsharing.carsharing.service.BookingService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingResolver implements GraphQLQueryResolver {

    private final BookingService bookingService;

    public BookingResolver(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public List<BookingDTO> allBookings() {
        return bookingService.getAllBookingsDTO();
    }

    public BookingDTO bookingById(Long id) {
        return bookingService.getBookingsByIdDTO(id);
    }

    public BookingDTO addBooking(BookingDTO bookingDTO) {
        return bookingService.saveBookingDTO(bookingDTO);
    }

    public Boolean deleteBooking(Long id) {
        bookingService.deleteBookingDTO(id);
        return true;
    }

}
