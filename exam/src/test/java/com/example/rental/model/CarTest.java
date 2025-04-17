package com.example.rental.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void constructor_and_getters() {
        Car car = new Car("REG123", "Peugeot 208", true);
        assertEquals("REG123", car.getRegistrationNumber());
        assertEquals("Peugeot 208", car.getModel());
        assertTrue(car.isAvailable());
    }

    @Test
    void setAvailable_changesAvailability() {
        Car car = new Car("XYZ999", "Renault Clio", false);
        assertFalse(car.isAvailable());

        car.setAvailable(true);
        assertTrue(car.isAvailable());

        car.setAvailable(false);
        assertFalse(car.isAvailable());
    }
}
