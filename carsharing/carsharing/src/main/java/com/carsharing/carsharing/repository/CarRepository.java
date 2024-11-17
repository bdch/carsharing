package com.carsharing.carsharing.repository;

import com.carsharing.carsharing.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// **Annotation zur Kennzeichnung als Repository**
@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

    // `JpaRepository<Car, Long>`:
    // - `Car`: Die Entität, die das Repository verwaltet.
    // - `Long`: Der Datentyp des Primärschlüssels (ID) der Entität.

    // Dieses Interface erbt von JpaRepository und bietet daher CRUD-Operationen sowie Paging- und Sortierfunktionen für die Car-Entität.

}
