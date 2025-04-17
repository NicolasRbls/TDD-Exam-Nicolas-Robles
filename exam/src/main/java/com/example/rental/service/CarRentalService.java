package com.example.rental.service;

import com.example.rental.model.Car;
import com.example.rental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarRentalService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public boolean rentCar(String registrationNumber) {
        Optional<Car> carOpt = carRepository.findByRegistrationNumber(registrationNumber);
        if (carOpt.isPresent() && carOpt.get().isAvailable()) {
            Car car = carOpt.get();
            car.setAvailable(false);
            carRepository.updateCar(car);
            return true;
        }
        return false;
    }

    public void returnCar(String registrationNumber) {
        Optional<Car> carOpt = carRepository.findByRegistrationNumber(registrationNumber);
        carOpt.ifPresent(car -> {
            car.setAvailable(true);
            carRepository.updateCar(car);
        });
    }
}
