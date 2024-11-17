package com.carsharing.carsharing.dto;

// Die Klasse BookingDTO repräsentiert eine Datenübertragungsstruktur (Data Transfer Object)
// für Buchungen im Carsharing-System. Sie dient dazu, Daten zwischen der API und dem Client
// in einer leicht verständlichen und flachen Struktur zu übertragen.
public class BookingDTO {

    private Long id;

    private Long carId;

    private CarDTO car;
    private String customerName;
    private String startTime;
    private String endTime;


    // **Standardkonstruktor**: Erforderlich für Frameworks wie Spring oder Hibernate,
    // da sie Objekte oft mit einem leeren Konstruktor erstellen.
    public BookingDTO() {}

    // **Konstruktor mit Parametern**: Ermöglicht die direkte Initialisierung eines
    // BookingDTO-Objekts mit allen wichtigen Eigenschaften.
    public BookingDTO(Long id, Long carId, String startTime, String endTime, String customerName) {
        this.id = id;
        this.carId = carId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerName = customerName;
    }

    // Getter und Setter: Methoden, die den Zugriff auf private Felder ermöglichen.
    // Getter gibt den aktuellen Wert eines Feldes zurück.
    // Setter aktualisiert den Wert eines Feldes.

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
