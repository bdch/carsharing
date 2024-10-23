package com.carsharing.carsharing.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String customerName;

    /**
     * default Constructor
     */
    public Booking() {
    }


    /**
     * Constructor
     * @param car The car that is booked
     * @param startTime The start date of the booking
     * @param endTime The end date of the booking
     */
    public Booking(Car car, LocalDateTime startTime, LocalDateTime endTime, String customerName) {
        this.car = car;
        this.customerName = customerName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Get the id of the booking
     * @return The id of the booking
     */
    public long getId() {
        return id;
    }

    /**
     * Set the id of the booking
     * @param id The id of the booking
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the car that is booked
     * @return The car that is booked
     */
    public Car getCar() {
        return car;
    }

    /**
     * Set the car that is booked
     * @param car The car that is booked
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * set the name of the customer
     * @param customerName The name of the customer
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Get the name of the customer
     * @return The name of the customer
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Get the start date of the booking
     * @return The start date of the booking
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Set the start date of the booking
     * @param startTime The start date of the booking
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Get the end date of the booking
     * @return The end date of the booking
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Set the end date of the booking
     * @param endTime The end date of the booking
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Get the name of the customer
     * @return The name of the customer
     */
    public String findByCustomerName(String customerName){
        return customerName;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", car=" + car +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
