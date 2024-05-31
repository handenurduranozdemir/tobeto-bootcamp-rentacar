package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.TransmissionService;
import com.tobeto.rentacar.business.dtos.request.transmission.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.request.transmission.UpdateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.transmission.CreatedTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.GetAllTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.GetTransmissionByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.UpdateTransmissionResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/transmissions")
@CrossOrigin(origins = "http://localhost:4200")
public class TransmissionsController {
    private TransmissionService transmissionService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedTransmissionResponse createTransmission(@Valid @RequestBody CreateTransmissionRequest transmissionRequest) {
        return transmissionService.createTransmission(transmissionRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllTransmissionResponse> getAllTransmissions() {
        return transmissionService.getAllTransmissions();
    }

    @GetMapping("/{id}")
    public GetTransmissionByIdResponse getTransmissionById(@PathVariable int id) {
        return transmissionService.getTransmissionById(id);
    }

    @PutMapping("/{id}")
    public UpdateTransmissionResponse updateTransmissionById(@RequestBody UpdateTransmissionRequest transmissionRequest, @PathVariable int id) {
        return transmissionService.updateTransmissionById(transmissionRequest,id);
    }
}