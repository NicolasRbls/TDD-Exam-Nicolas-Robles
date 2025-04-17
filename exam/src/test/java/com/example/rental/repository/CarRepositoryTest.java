package com.example.rental.repository;

import com.example.rental.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

    private CarRepository repository;

    @BeforeEach
    void setUp() {
        repository = new CarRepository();
    }

    @Test
    void getAllCars_emptyAtStart() {
        List<Car> cars = repository.getAllCars();
        assertTrue(cars.isEmpty(), "La liste doit être vide au démarrage");
    }

    @Test
    void addCar_and_findByRegistrationNumber() {
        Car car = new Car("ABC123", "Toyota", true);
        repository.addCar(car);

        List<Car> all = repository.getAllCars();
        assertEquals(1, all.size());
        assertEquals("ABC123", all.get(0).getRegistrationNumber());

        Optional<Car> found = repository.findByRegistrationNumber("ABC123");
        assertTrue(found.isPresent());
        assertEquals("Toyota", found.get().getModel());
    }

    @Test
    void findByRegistrationNumber_notFound() {
        Optional<Car> notFound = repository.findByRegistrationNumber("ZZZ999");
        assertFalse(notFound.isPresent());
    }

    @Test
    void updateCar_replacesExisting() {
        Car car = new Car("XYZ789", "Ford", true);
        repository.addCar(car);

        car.setAvailable(false);
        repository.updateCar(car);

        Optional<Car> updated = repository.findByRegistrationNumber("XYZ789");
        assertTrue(updated.isPresent());
        assertFalse(updated.get().isAvailable());
    }
}
