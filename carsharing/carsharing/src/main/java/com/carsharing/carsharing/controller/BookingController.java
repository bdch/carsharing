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

// @RestController zeigt an, dass diese Klasse eine REST-API-Controller-Klasse ist.
// Sie verarbeitet HTTP-Anfragen und gibt HTTP-Antworten zurück.
@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    // Der Service enthält die Geschäftslogik. Er wird hier per Dependency Injection eingebunden.
    @Autowired
    private BookingService bookingService;

    // Das Repository wird für direkten Datenbankzugriff verwendet, wenn nötig.
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public List<BookingDTO> getAllBookings() {
        // Ruft alle Buchungen als DTOs (Data Transfer Objects) ab.
        // DTOs sind spezielle Objekte, die nur relevante Daten enthalten.
        return bookingService.getAllBookingsDTO();
    }

    @GetMapping("/{id}")
    public BookingDTO getBookingById(@PathVariable Long id) {
        // @PathVariable bindet den Wert der URL (z. B. /bookings/1) an die id-Variable.
        return bookingService.getBookingsByIdDTO(id);
    }

    @GetMapping("/customer/{customerName}")
    public List<BookingDTO> getBookingByCustomerName(@PathVariable String customerName) {
        // Sucht alle Buchungen, konvertiert sie von Entity (Datenbankformat) in DTO (API-Format).
        return bookingService.getBookingByCustomerName(customerName)
                .stream() // Konvertiert die Liste in einen Stream (zur Transformation geeignet).
                .map(BookingMapper.INSTANCE::bookingToBookingDTO) // Entity -> DTO
                .collect(Collectors.toList()); // Stream -> Liste
    }

    @PostMapping
    public BookingDTO createBooking(@RequestBody BookingDTO bookingDTO) {
        // @RequestBody bindet den JSON-Body der Anfrage an ein BookingDTO-Objekt.
        return bookingService.createBooking(bookingDTO);
    }

    @PutMapping("/{id}")
    public BookingDTO updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        // Aktualisiert die Buchung mit der angegebenen ID basierend auf den übergebenen Daten.
        return bookingService.updateBooking(id, bookingDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        // Führt die Löschlogik aus (z. B. Buchung aus der Datenbank entfernen).
        bookingService.deleteBooking(id);
    }

}
