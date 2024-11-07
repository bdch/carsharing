package com.carsharing.carsharing.service;

import com.carsharing.carsharing.Mapper.BookingMapper;
import com.carsharing.carsharing.dto.BookingDTO;
import com.carsharing.carsharing.model.Car;
import com.carsharing.carsharing.repository.BookingRepository;
import com.carsharing.carsharing.model.Booking;
import com.carsharing.carsharing.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private final CarRepository carRepository;

    // injection via constructor
    public BookingService(CarRepository carRepository, BookingRepository bookingRepository) {
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
    }


    public List<Booking> getAllBookings(){ return bookingRepository.findAll();    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking saveBooking(Booking booking) { return bookingRepository.save(booking);    }

    public Booking addBooking(Booking booking) { return bookingRepository.save(booking);}

    public List<Booking> getBookingByCustomerName(String customerName) { return bookingRepository.findByCustomerName(customerName);    }

    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
        throw new ResourceNotFoundException("Booking not found with id: " + id);
    }
        bookingRepository.deleteById(id);
    }

    public List<BookingDTO> getAllBookingsDTO() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(BookingMapper.INSTANCE::bookingToBookingDTO) // Entity -> DTO
                .collect(Collectors.toList());
    }

    public BookingDTO getBookingsByIdDTO(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
        return BookingMapper.INSTANCE.bookingToBookingDTO(booking);
    }

    // Entgegennahme eines BookingDTOs und Rückgabe als DTO
    public BookingDTO saveBookingDTO(BookingDTO bookingDTO) {
        Booking booking = BookingMapper.INSTANCE.bookingDTOToBooking(bookingDTO);
        Booking savedBooking = bookingRepository.save(booking);
        return BookingMapper.INSTANCE.bookingToBookingDTO(savedBooking);
    }

    // Löschen eines Buchungsobjekts durch DTO
    public void deleteBookingDTO(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);
    }

}
