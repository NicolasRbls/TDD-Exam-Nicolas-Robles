package com.example.rental.controller;

import com.example.rental.model.Car;
import com.example.rental.service.CarRentalService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
	@MockBean
    private CarRentalService service;

    @Test
    void getAllCars_returnsJsonArray() throws Exception {
        List<Car> cars = List.of(
            new Car("R1", "Renault", true),
            new Car("F2", "Fiat", false)
        );
        Mockito.when(service.getAllCars()).thenReturn(cars);

        mockMvc.perform(get("/cars"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$[0].registrationNumber").value("R1"))
               .andExpect(jsonPath("$[1].model").value("Fiat"));
    }

    @Test
    void rentCar_delegatesToService() throws Exception {
        Mockito.when(service.rentCar("R1")).thenReturn(true);

        mockMvc.perform(post("/cars/rent/R1"))
               .andExpect(status().isOk())
               .andExpect(content().string("true"));

        verify(service).rentCar(eq("R1"));
    }

    @Test
    void returnCar_delegatesToService() throws Exception {
        mockMvc.perform(post("/cars/return/X9"))
               .andExpect(status().isOk())
               .andExpect(content().string(""));

        verify(service).returnCar(eq("X9"));
    }
}
