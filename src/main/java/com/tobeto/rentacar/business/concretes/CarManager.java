package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.CarService;
import com.tobeto.rentacar.business.dtos.request.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedCarResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllCarResponse;
import com.tobeto.rentacar.core.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.CarRepository;
import com.tobeto.rentacar.entities.conretes.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private ModelMapperService modelMapperService;
    private CarRepository carRepository;

    @Override
    public CreatedCarResponse add(CreateCarRequest createCarRequest) {
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
        car.setCreatedDate(LocalDateTime.now());
        Car createdCar = this.carRepository.save(car);
        CreatedCarResponse createdCarResponse =
                this.modelMapperService.forResponse().map(createdCar, CreatedCarResponse.class);
        return createdCarResponse;
    }

    @Override
    public List<GetAllCarResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarResponse> carResponses = cars.stream().map(car -> modelMapperService.forResponse()
                .map(car, GetAllCarResponse.class)).toList();
        return carResponses;
    }
}