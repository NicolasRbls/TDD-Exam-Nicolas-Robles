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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;


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

    @Test
    void addCar_successful() {
        Car newCar = new Car("C3","Opel",true);
        when(repo.findByRegistrationNumber("C3")).thenReturn(Optional.empty());

        boolean result = service.addCar(newCar);

        assertTrue(result);
        verify(repo).addCar(newCar);
    }

    @Test
    void addCar_duplicateRegistration() {
        Car existing = new Car("C3","Opel",true);
        when(repo.findByRegistrationNumber("C3")).thenReturn(Optional.of(existing));

        boolean result = service.addCar(existing);

        assertFalse(result);
        verify(repo, never()).addCar(any());
    }

    @Test
    void findCarsByModel_returnsMatches() {
        Car carA = new Car("A1","Golf",true);
        Car carB = new Car("B2","Polo",true);
        Car carC = new Car("C3","Golf",false);
        when(repo.getAllCars()).thenReturn(Arrays.asList(carA,carB,carC));

        List<Car> result = service.findCarsByModel("Golf");

        assertEquals(2, result.size());
        assertTrue(result.contains(carA));
        assertTrue(result.contains(carC));
    }

    @Test
    void findCarsByModel_noMatches() {
        when(repo.getAllCars()).thenReturn(Collections.emptyList());

        List<Car> result = service.findCarsByModel("Any");

        assertTrue(result.isEmpty());
    }
}
