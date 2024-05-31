package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.CarService;
import com.tobeto.rentacar.business.dtos.request.car.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.request.car.UpdateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.car.CreatedCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetAllCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetCarByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.car.UpdateCarResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/cars")
@CrossOrigin(origins = "http://localhost:4200")
public class CarsController {
    private CarService carService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCarResponse createCar(@Valid @RequestBody CreateCarRequest carRequest) {
        return carService.createCar(carRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCarResponse> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public GetCarByIdResponse getCarById(@PathVariable int id){
        return carService.getCarById(id);
    }

    @PutMapping("/{id}")
    public UpdateCarResponse updateCarById(@RequestBody UpdateCarRequest carRequest, @PathVariable int id){
        return carService.updateCarById(carRequest,id);
    }

    @DeleteMapping("/{id}")
    void deleteCarById(@PathVariable int id){
        carService.deleteCarById(id);
    }
}