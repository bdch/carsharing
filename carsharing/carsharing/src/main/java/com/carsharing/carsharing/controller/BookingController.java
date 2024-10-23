package com.carsharing.carsharing.controller;


import com.carsharing.carsharing.model.Booking;
import com.carsharing.carsharing.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    //Get all bookings
    @GetMapping
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }

    //Get booking by id
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }

    //Get booking by customer name
    @GetMapping("/customer/{customerName}")
    public List<Booking> getBookingByCustomerName(@PathVariable String customerName){
        return bookingService.getBookingByCustomerName(customerName);
    }

    //add a new booking
    @PostMapping
    public Booking addBooking(@RequestBody Booking booking){
        return bookingService.addBooking(booking);
    }

    //update a booking id
    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails){
        Booking booking = bookingService.getBookingById(id);
        if(booking != null) {
            booking.setCar(bookingDetails.getCar());
            booking.setStartTime(bookingDetails.getStartTime());
            booking.setEndTime(bookingDetails.getEndTime());
            booking.setCustomerName(bookingDetails.getCustomerName());
            return bookingService.saveBooking(booking);
        }
        throw new NullPointerException("Booking not found");
    }

    //delete a booking by id
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
    }



}
