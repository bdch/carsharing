package com.carsharing.carsharing.controller;


import com.carsharing.carsharing.Mapper.BookingMapper;
import com.carsharing.carsharing.dto.BookingDTO;
import com.carsharing.carsharing.model.Booking;
import com.carsharing.carsharing.repository.BookingRepository;
import com.carsharing.carsharing.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public List<BookingDTO> getAllBookings() {
        return bookingService.getAllBookingsDTO();
    }

    @GetMapping("/{id}")
    public BookingDTO getBookingById(@PathVariable Long id) {
        return bookingService.getBookingsByIdDTO(id);
    }

    @GetMapping("/customer/{customerName}")
    public List<BookingDTO> getBookingByCustomerName(@PathVariable String customerName) {
        return bookingService.getBookingByCustomerName(customerName)
                .stream()
                .map(BookingMapper.INSTANCE::bookingToBookingDTO) // Entity -> DTO
                .collect(Collectors.toList());
    }

    @PostMapping
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }

    @PutMapping("/{id}")
    public BookingDTO updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        return bookingService.updateBooking(id, bookingDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }

}
