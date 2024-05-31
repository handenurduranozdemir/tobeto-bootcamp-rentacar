package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.FuelService;
import com.tobeto.rentacar.business.dtos.request.fuel.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.request.fuel.UpdateFuelRequest;
import com.tobeto.rentacar.business.dtos.responses.fuel.CreatedFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.GetAllFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.GetFuelByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.UpdateFuelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fuels")
@CrossOrigin(origins = "http://localhost:4200")
public class FuelsController {
    private FuelService fuelService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedFuelResponse createFuel(@Valid @RequestBody CreateFuelRequest fuelRequest) {
        return fuelService.createFuel(fuelRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllFuelResponse> getAllFuels() {
        return fuelService.getAllFuels();
    }

    @GetMapping("/{id}")
    public GetFuelByIdResponse getFuelById(@PathVariable int id){
        return fuelService.getFuelById(id);
    }
    @PutMapping("/{id}")
    public UpdateFuelResponse updateFuelById(@RequestBody UpdateFuelRequest fuelRequest, @PathVariable int id){
        return fuelService.updateFuelById(fuelRequest,id);
    }
}