package com.example.rental.repository;

import com.example.rental.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CarRepository {
    private final List<Car> cars = new ArrayList<>();

    public List<Car> getAllCars() {
        return new ArrayList<>(cars);
    }

    public Optional<Car> findByRegistrationNumber(String registrationNumber) {
        return cars.stream()
                   .filter(c -> c.getRegistrationNumber().equals(registrationNumber))
                   .findFirst();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void updateCar(Car car) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getRegistrationNumber().equals(car.getRegistrationNumber())) {
                cars.set(i, car);
                return;
            }
        }
    }
}
