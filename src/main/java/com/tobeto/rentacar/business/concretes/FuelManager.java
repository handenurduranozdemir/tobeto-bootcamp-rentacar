package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.FuelService;
import com.tobeto.rentacar.business.dtos.request.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.CreatedFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllFuelResponse;
import com.tobeto.rentacar.core.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.FuelRepository;
import com.tobeto.rentacar.entities.conretes.Brand;
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

    @Override
    public CreatedFuelResponse add(CreateFuelRequest createFuelRequest) {
        Fuel fuel = this.modelMapperService.forRequest().map(createFuelRequest, Fuel.class);
        fuel.setCreatedDate(LocalDateTime.now());
        Fuel createdFuel = this.fuelRepository.save(fuel);
        CreatedFuelResponse createdFuelResponse =
                this.modelMapperService.forResponse().map(createdFuel, CreatedFuelResponse.class);
        return createdFuelResponse;
    }

    @Override
    public List<GetAllFuelResponse> getAll() {
        List<Fuel> fuels = fuelRepository.findAll();
        List<GetAllFuelResponse> fuelResponses = fuels.stream().map(fuel -> modelMapperService.forResponse()
                .map(fuel, GetAllFuelResponse.class)).toList();
        return fuelResponses;
    }
}