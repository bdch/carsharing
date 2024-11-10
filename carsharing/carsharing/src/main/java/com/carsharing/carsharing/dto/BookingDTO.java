package com.carsharing.carsharing.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookingDTO {

    private Long id;

    private Long carId;

    private CarDTO car;
    private String customerName;
    private String startTime;
    private String endTime;


    public BookingDTO() {}

    public BookingDTO(Long id, Long carId, String startTime, String endTime, String customerName) {
        this.id = id;
        this.carId = carId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerName = customerName;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
