package com.carsharing.carsharing.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
    @Table(name = "cars")
    public class Car {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) //set the id to be generated automatically
        private Long id;

        @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonIgnore
        private List<Booking> bookings = new ArrayList<>();


        private String brand;
        private String model;
        private String licensePlate;

        /**
         *  default Constructor
         */
        public Car() {
        }

        /**
         * Constructor
         * @param brand The brand of the car
         * @param model The model of the car
         * @param licensePlate The license plate of the car
         */
        public Car(String brand, String model, String licensePlate) {
            this.brand = brand;
            this.model = model;
            this.licensePlate = licensePlate;
        }

        /**
         * Get the id of the car
         * @return The id of the car
         */
        public Long getId() {
            return id;
        }


        /**
         * set the id of the car
         * @param id The id of the car
         */
        public void setId(Long id) {
            this.id = id;
        }

        /**
         * Get the brand of the car
         * @return The brand of the car
         */
        public String getBrand() {
            return brand;
        }

        /**
         * Set the brand of the car
         * @param brand The brand of the car
         */
        public void setBrand(String brand) {
            this.brand = brand;
        }

        /**
         * Get the model of the car
         * @return The model of the car
         */
        public String getModel() {
            return model;
        }

        /**
         * Set the model of the car
         * @param model The model of the car
         */
        public void setModel(String model) {
            this.model = model;
        }

        /**
         * Get the license plate of the car
         * @return The license plate of the car
         */
        public String getLicensePlate() {
            return licensePlate;
        }

        /**
         * Set the license plate of the car
         * @param licensePlate The license plate of the car
         */
        public void setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "id=" + id +
                    ", brand='" + brand + '\'' +
                    ", model='" + model + '\'' +
                    ", licensePlate='" + licensePlate + '\'' +
                    '}';
        }



}

