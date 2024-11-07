package com.carsharing.carsharing.controller;


import com.carsharing.carsharing.Mapper.BookingMapper;
import com.carsharing.carsharing.dto.BookingDTO;
import com.carsharing.carsharing.model.Booking;
import com.carsharing.carsharing.repository.BookingRepository;
import com.carsharing.carsharing.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Get all bookings
    @GetMapping
    public List<BookingDTO> getAllBookings() {
        return bookingService.getAllBookingsDTO();
    }

    // Buchung nach ID als DTO zurückgeben
    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingsByIdDTO(id));
    }

    // Buchung nach Kundenname
    @GetMapping("/customer/{customerName}")
    public List<BookingDTO> getBookingByCustomerName(@PathVariable String customerName) {
        return bookingService.getBookingByCustomerName(customerName)
                .stream()
                .map(booking -> BookingMapper.INSTANCE.bookingToBookingDTO(booking)) // Entity -> DTO
                .collect(Collectors.toList());
    }

    // Neue Buchung mit DTO anlegen
    @PostMapping
    public ResponseEntity<BookingDTO> addBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBookingDTO = bookingService.saveBookingDTO(bookingDTO);
        return ResponseEntity.status(201).body(createdBookingDTO);
    }

    // Buchung aktualisieren mit DTO
    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDetails) {
        BookingDTO updatedBookingDTO = bookingService.saveBookingDTO(bookingDetails);
        return ResponseEntity.ok(updatedBookingDTO);
    }

    // Buchung löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBookingDTO(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
