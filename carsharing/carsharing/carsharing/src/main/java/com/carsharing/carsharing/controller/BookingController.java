package com.carsharing.carsharing.controller;


import com.carsharing.carsharing.model.Booking;
import com.carsharing.carsharing.repository.BookingRepository;
import com.carsharing.carsharing.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    //Get all bookings
    @GetMapping
    public List<Booking> getAllBookings(){
       return bookingRepository.findAll();
    }

    //Get booking by id
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id){
       return bookingRepository.findById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    //Get booking by customer name
    @GetMapping("/customer/{customerName}")
    public List<Booking> getBookingByCustomerName(@PathVariable String customerName){
        return bookingService.getBookingByCustomerName(customerName);
    }

    //add a new booking
    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking){
        Booking createdBooking = bookingService.addBooking(booking);
        return ResponseEntity.status(201).body(createdBooking);
    }

    //update a booking id
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails){
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
            booking.setCar(bookingDetails.getCar());
            booking.setStartTime(bookingDetails.getStartTime());
            booking.setEndTime(bookingDetails.getEndTime());
            booking.setCustomerName(bookingDetails.getCustomerName());
            Booking updatedBooking = bookingService.saveBooking(booking);
            return ResponseEntity.ok(updatedBooking);
    }

    //delete a booking by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id){
        if(bookingService.getBookingById(id) !=null) {
            bookingService.deleteBooking(id);
            return ResponseEntity.noContent().build(); //204 No Content
        }
        return ResponseEntity.notFound().build(); //404 Not Found
    }



}
