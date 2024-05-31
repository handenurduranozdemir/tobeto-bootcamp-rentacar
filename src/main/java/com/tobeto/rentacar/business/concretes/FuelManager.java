package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.FuelService;
import com.tobeto.rentacar.business.dtos.request.fuel.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.request.fuel.UpdateFuelRequest;
import com.tobeto.rentacar.business.dtos.responses.fuel.CreatedFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.GetAllFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.GetFuelByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.UpdateFuelResponse;
import com.tobeto.rentacar.business.rules.FuelBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.FuelRepository;
import com.tobeto.rentacar.entities.conretes.Fuel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FuelManager implements FuelService {
    private ModelMapperService modelMapperService;
    private FuelRepository fuelRepository;
    private FuelBusinessRules fuelBusinessRules;

    @Override
    public CreatedFuelResponse createFuel(CreateFuelRequest fuelRequest) {
        fuelBusinessRules.fuelNameCanNotBeDuplicated(fuelRequest.getName());

        Fuel fuel = this.modelMapperService.forRequest().map(fuelRequest, Fuel.class);
        fuel.setCreatedDate(LocalDateTime.now());
        Fuel createdFuel = this.fuelRepository.save(fuel);
        CreatedFuelResponse fuelResponse =
                this.modelMapperService.forResponse().map(createdFuel, CreatedFuelResponse.class);
        return fuelResponse;
    }

    @Override
    public List<GetAllFuelResponse> getAllFuels() {
        List<Fuel> fuels = fuelRepository.findAll();
        List<GetAllFuelResponse> fuelResponses = fuels.stream().map(fuel -> modelMapperService.forResponse()
                .map(fuel, GetAllFuelResponse.class)).toList();
        return fuelResponses;
    }

    @Override
    public GetFuelByIdResponse getFuelById(int id) {
        Fuel fuel = fuelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Fuel for this id"));
        GetFuelByIdResponse fuelResponse = modelMapperService.forResponse()
                .map(fuel, GetFuelByIdResponse.class);
        return fuelResponse;
    }

    @Override
    public UpdateFuelResponse updateFuelById(UpdateFuelRequest fuelRequest, int id) {
        Fuel fuel = fuelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no fuel for this id"));
        Fuel updatedFuel = modelMapperService.forRequest().map(fuelRequest, Fuel.class);

        fuel.setId(id);
        fuel.setUpdatedDate(LocalDateTime.now());
        fuel.setName(updatedFuel.getName());

        fuelRepository.save(fuel);

        UpdateFuelResponse fuelResponse = modelMapperService.forResponse()
                .map(fuel, UpdateFuelResponse.class);
        return fuelResponse;
    }
}