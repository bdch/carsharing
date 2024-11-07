package com.carsharing.carsharing.resolver;

import com.carsharing.carsharing.dto.BookingDTO;
import com.carsharing.carsharing.service.BookingService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class BookingMutationResolver implements GraphQLMutationResolver {

    private final BookingService bookingService;

    public BookingMutationResolver(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingDTO addBooking(BookingDTO bookingDTO) {
        return bookingService.saveBookingDTO(bookingDTO);
    }

    public Boolean deleteBooking(Long id) {
        bookingService.deleteBookingDTO(id);
        return true;
    }
}

