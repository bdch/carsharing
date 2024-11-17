package com.carsharing.carsharing.repository;

import com.carsharing.carsharing.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// **Annotation zur Kennzeichnung als Repository**
@Repository
// Diese Annotation markiert das Interface als Spring-Repository.
// Es signalisiert Spring, dass diese Klasse für die Interaktion mit der Datenbank verantwortlich ist.
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // `JpaRepository`: Bietet Standardmethoden für CRUD-Operationen (z. B. `save`, `findById`, `delete`).

    /**
     * Custom Query-Methode: Findet alle Buchungen eines bestimmten Kunden anhand des Kundennamens.
     * Spring Data JPA interpretiert den Methodennamen automatisch und generiert die entsprechende SQL-Abfrage:
     * SELECT * FROM bookings WHERE customer_name = :customerName
     *
     * @param customerName Der Name des Kunden, nach dem gesucht werden soll.
     * @return Eine Liste aller Buchungen, die zu diesem Kundennamen gehören.
     */

    List<Booking> findByCustomerName(String customerName);
}
