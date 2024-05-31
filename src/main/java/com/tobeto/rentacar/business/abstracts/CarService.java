package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.request.car.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.request.car.UpdateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.car.CreatedCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetAllCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetCarByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.car.UpdateCarResponse;

import java.util.List;

public interface CarService {
    CreatedCarResponse createCar(CreateCarRequest createCarRequest);
    List<GetAllCarResponse> getAllCars();
    GetCarByIdResponse getCarById(int id);
    UpdateCarResponse updateCarById(UpdateCarRequest updateCarRequest, int id);
    void deleteCarById(int id);
}