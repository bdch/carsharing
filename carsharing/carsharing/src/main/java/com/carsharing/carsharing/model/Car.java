package com.carsharing.carsharing.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

    // **Annotationen für Datenbank-Mapping und Entitätserstellung**
    @Entity // Kennzeichnet die Klasse als eine JPA-Entität, die einer Datenbanktabelle entspricht.
    @Table(name = "cars") // Gibt an, dass diese Entität mit der Tabelle "cars" in der Datenbank verknüpft ist.
    public class Car {

        // **Primärschlüssel**
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) //set the id to be generated automatically
        private Long id;

        // **Beziehung zu Booking**
        @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
        // Eine 1:n-Beziehung (ein Auto kann mehrere Buchungen haben).
        // `mappedBy = "car"`: Das Feld `car` in der Klasse `Booking` definiert die Beziehung.
        @JsonIgnore
        // Verhindert, dass die Liste der Buchungen (`bookings`) in der JSON-Ausgabe enthalten ist.
        private List<Booking> bookings = new ArrayList<>();
        // Initialisiert eine leere Liste von Buchungen, um NullPointerExceptions zu vermeiden.
        // Die Liste wird nur verwendet, um Buchungen zu speichern, die in der Datenbank gespeichert werden.



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

