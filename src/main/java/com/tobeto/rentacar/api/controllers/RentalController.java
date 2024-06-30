package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.RentalService;
import com.tobeto.rentacar.business.dtos.request.rental.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.request.rental.UpdateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.rental.CreatedRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetAllRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetRentalByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.UpdateRentalResponse;
import com.tobeto.rentacar.entities.conretes.Rental;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rentals")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedRentalResponse createRental(@Valid @RequestBody CreateRentalRequest rentalRequest) {
        return rentalService.createRental(rentalRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllRentalResponse> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public GetRentalByIdResponse getRentalById(@PathVariable int id){
        return rentalService.getRentalById(id);
    }

    @PutMapping("/{id}")
    public UpdateRentalResponse updateRentalById(@RequestBody UpdateRentalRequest rentalRequest, @PathVariable int id){
        return rentalService.updateRentalById(rentalRequest, id);
    }

    @DeleteMapping("/{id}")
    void deleteRentalById(@PathVariable int id){
        rentalService.deleteRentalById(id);
    }
}
