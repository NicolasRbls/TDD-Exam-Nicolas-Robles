package com.example.rental.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.java.en.*;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.rental.RentalApplication;
import com.example.rental.model.Car;
import com.example.rental.repository.CarRepository;
import com.example.rental.service.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@CucumberContextConfiguration
@SpringBootTest(classes = RentalApplication.class,
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarRentalSteps {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarRentalService service;

    private List<Car> listResult;
    private boolean rentResult;

    @Given("des voitures sont disponibles")
    public void des_voitures_sont_disponibles() {
        carRepository.getAllCars().clear();
        carRepository.addCar(new Car("R1","Renault",true));
        carRepository.addCar(new Car("F2","Fiat",true));
    }

    @When("je demande la liste des voitures")
    public void je_demande_la_liste_des_voitures() {
        listResult = service.getAllCars();
    }

    @Then("toutes les voitures sont affichées")
    public void toutes_les_voitures_sont_affichées() {
        assertNotNull(listResult);
        assertEquals(2, listResult.size());
    }

    @Given("une voiture est disponible")
    public void une_voiture_est_disponible() {
        carRepository.getAllCars().clear();
        carRepository.addCar(new Car("A1","Tesla",true));
    }

    @When("je loue cette voiture")
    public void je_loue_cette_voiture() {
        rentResult = service.rentCar("A1");
    }

    @Then("la voiture n'est plus disponible")
    public void la_voiture_nest_plus_disponible() {
        assertTrue(rentResult);
        Car car = carRepository.findByRegistrationNumber("A1").orElseThrow();
        assertFalse(car.isAvailable());
    }

    @Given("une voiture est louée")
    public void une_voiture_est_louée() {
        carRepository.getAllCars().clear();
        carRepository.addCar(new Car("B1","BMW",false));
    }

    @When("je retourne cette voiture")
    public void je_retourne_cette_voiture() {
        service.returnCar("B1");
    }

    @Then("la voiture est marquée comme disponible")
    public void la_voiture_est_marquée_comme_disponible() {
        Car car = carRepository.findByRegistrationNumber("B1").orElseThrow();
        assertTrue(car.isAvailable());
    }
}
