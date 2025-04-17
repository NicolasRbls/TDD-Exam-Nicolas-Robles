// src/test/java/com/example/rental/service/CarRentalServiceTest.java
package com.example.rental.service;

import com.example.rental.model.Car;
import com.example.rental.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;               
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;               

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // charge le MockitoExtension de JUnit5
class CarRentalServiceTest {

    @Mock
    private CarRepository repo;

    @InjectMocks
    private CarRentalService service;

    private Car availableCar;
    private Car rentedCar;

    @BeforeEach
    void setUp() {
        availableCar = new Car("A1", "Tesla", true);
        rentedCar    = new Car("B2", "BMW", false);
    }

    // Test de la méthode getAllCars
    @Test
    void rentCar_successful() {
        when(repo.findByRegistrationNumber("A1")).thenReturn(Optional.of(availableCar));

        boolean result = service.rentCar("A1");

        assertTrue(result);
        assertFalse(availableCar.isAvailable());
        verify(repo).updateCar(availableCar);
    }

    // Test de la méthode rentCar avec une voiture déjà louée
    @Test
    void rentCar_notAvailable() {
        when(repo.findByRegistrationNumber("B2")).thenReturn(Optional.of(rentedCar));

        boolean result = service.rentCar("B2");

        assertFalse(result);
        verify(repo, never()).updateCar(any());
    }

    // Test de la méthode rentCar avec une voiture non trouvée
    @Test
    void rentCar_notFound() {
        when(repo.findByRegistrationNumber("C3")).thenReturn(Optional.empty());

        assertFalse(service.rentCar("C3"));
        verify(repo, never()).updateCar(any());
    }

    // Test de la méthode getAllCars
    @Test
    void returnCar_marksAvailable() {
        Car spyCar = spy(new Car("D4", "Audi", false));
        when(repo.findByRegistrationNumber("D4")).thenReturn(Optional.of(spyCar));

        service.returnCar("D4");

        assertTrue(spyCar.isAvailable());
        verify(repo).updateCar(spyCar);
    }
}
