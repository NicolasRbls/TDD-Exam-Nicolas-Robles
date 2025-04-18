package com.example.rental.controller;

import com.example.rental.model.Car;
import com.example.rental.service.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRentalService carRentalService;

    @GetMapping
    public List<Car> getAllCars() {
        return carRentalService.getAllCars();
    }

    @PostMapping("/rent/{registrationNumber}")
    public boolean rentCar(@PathVariable String registrationNumber) {
        return carRentalService.rentCar(registrationNumber);
    }

    @PostMapping("/return/{registrationNumber}")
    public void returnCar(@PathVariable String registrationNumber) {
        carRentalService.returnCar(registrationNumber);
    }

    @PostMapping("/add")
    public boolean addCar(@RequestBody Car car) {
        return carRentalService.addCar(car);
    }

    @GetMapping("/search")
    public List<Car> searchCarsByModel(@RequestParam String model) {
        return carRentalService.findCarsByModel(model);
    }
}
