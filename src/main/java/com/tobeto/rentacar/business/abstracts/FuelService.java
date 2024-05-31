package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.request.fuel.CreateFuelRequest;
import com.tobeto.rentacar.business.dtos.request.fuel.UpdateFuelRequest;
import com.tobeto.rentacar.business.dtos.responses.fuel.CreatedFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.GetAllFuelResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.GetFuelByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.fuel.UpdateFuelResponse;

import java.util.List;

public interface FuelService {
    CreatedFuelResponse createFuel(CreateFuelRequest createFuelRequest);
    List<GetAllFuelResponse> getAllFuels();
    GetFuelByIdResponse getFuelById(int id);
    UpdateFuelResponse updateFuelById(UpdateFuelRequest updateFuelRequest, int id);
}