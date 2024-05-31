package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.ModelService;
import com.tobeto.rentacar.business.dtos.request.model.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.request.model.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.model.CreatedModelResponse;
import com.tobeto.rentacar.business.dtos.responses.model.GetAllModelResponse;
import com.tobeto.rentacar.business.dtos.responses.model.GetModelByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.model.UpdateModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/models")
@CrossOrigin(origins = "http://localhost:4200")
public class ModelsController {

    private ModelService modelService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedModelResponse createModel(@Valid @RequestBody CreateModelRequest modelRequest) {
        return modelService.createModel(modelRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllModelResponse> getAllModels() {
        return modelService.getAllModels();
    }

    @GetMapping("/{id}")
    public GetModelByIdResponse getModelById(@PathVariable int id){
        return modelService.getModelById(id);
    }
    @PutMapping("/{id}")
    public UpdateModelResponse updateModelById(@RequestBody UpdateModelRequest modelRequest, @PathVariable int id) {
        return modelService.updateModelById(modelRequest,id);
    }
    @DeleteMapping("/{id}")
    public void deleteModelById(@PathVariable int id){
        modelService.deleteModelById(id);
    }
}