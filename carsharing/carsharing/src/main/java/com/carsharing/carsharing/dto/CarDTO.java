package com.carsharing.carsharing.dto;

// Die Klasse CarDTO repräsentiert ein Datenübertragungsobjekt (Data Transfer Object)
// für Autos im Carsharing-System. Sie dient dazu, Auto-Daten zwischen dem Server
// und dem Client in einer vereinfachten Struktur zu übertragen.
public class CarDTO {

    // **Felder**: Diese repräsentieren die Eigenschaften eines Autos.


    private Long id;
    private String brand;
    private String model;
    private String licensePlate;

    public CarDTO() {}

    // **Konstruktor mit Parametern**: Ermöglicht die direkte Erstellung eines Objekts
    // mit allen wichtigen Eigenschaften. Praktisch für Testfälle oder Initialisierung.
    public CarDTO(Long id,String brand, String model, String licensePlate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
    }

    // **Getter und Setter**: Diese Methoden werden verwendet, um auf die privaten Felder zuzugreifen
    // oder sie zu ändern. Sie folgen dem Prinzip der Kapselung, das direkte Änderungen an Feldern verhindert.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

}
