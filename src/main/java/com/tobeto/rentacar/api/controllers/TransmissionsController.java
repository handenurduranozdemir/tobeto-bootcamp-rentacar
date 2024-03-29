package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.TransmissionService;
import com.tobeto.rentacar.business.dtos.request.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllTransmissionResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/transmissions")
public class TransmissionsController {
    private TransmissionService transmissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedTransmissionResponse add(@Valid @RequestBody CreateTransmissionRequest createTransmissionRequest) {
        return transmissionService.add(createTransmissionRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllTransmissionResponse> getAll() {
        return transmissionService.getAll();
    }
}