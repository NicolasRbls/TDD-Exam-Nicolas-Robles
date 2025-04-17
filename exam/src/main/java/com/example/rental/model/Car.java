package com.example.rental.model;

public class Car {
    private String registrationNumber;
    private String model;
    private boolean available;

    public Car() { }

    public Car(String registrationNumber, String model, boolean available) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.available = available;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
