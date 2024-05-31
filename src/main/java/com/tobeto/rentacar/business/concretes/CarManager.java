package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.CarService;
import com.tobeto.rentacar.business.dtos.request.car.CreateCarRequest;
import com.tobeto.rentacar.business.dtos.request.car.UpdateCarRequest;
import com.tobeto.rentacar.business.dtos.responses.car.CreatedCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetAllCarResponse;
import com.tobeto.rentacar.business.dtos.responses.car.GetCarByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.car.UpdateCarResponse;
import com.tobeto.rentacar.business.rules.CarBusinessRules;
import com.tobeto.rentacar.business.rules.ModelBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.BrandRepository;
import com.tobeto.rentacar.dataAccess.abstracts.CarRepository;
import com.tobeto.rentacar.dataAccess.abstracts.ModelRepository;
import com.tobeto.rentacar.entities.conretes.Car;
import com.tobeto.rentacar.entities.conretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarManager.class);

    private ModelMapperService modelMapperService;
    private CarRepository carRepository;
    private final ModelRepository modelRepository;
    private CarBusinessRules carBusinessRules;
    private ModelBusinessRules modelBusinessRules;
    private final BrandRepository brandRepository;

    @Override
    public CreatedCarResponse createCar(CreateCarRequest carRequest) {
        carBusinessRules.carPlateCanNotBeDuplicated(carRequest.getPlate());
        modelBusinessRules.isModelExist(carRequest.getModelId());

        LOGGER.info("Adding new car with request: {}", carRequest);

        Car car = this.modelMapperService.forRequest().map(carRequest, Car.class);
        car.setCreatedDate(LocalDateTime.now());

        LOGGER.info("Attempting to fetch model with ID: {}", carRequest.getModelId());
        Model model = modelRepository.findById(carRequest.getModelId())
                .orElseThrow(() -> {
                    String errorMessage = String.format("Model with ID %d not found", carRequest.getModelId());
                    LOGGER.error(errorMessage);
                    return new RuntimeException(errorMessage);
                });

        LOGGER.info("Model found: {}", model);

        car.setModel(model);
        car.setId(0);
        Car createdCar = this.carRepository.save(car);

        LOGGER.info("Car saved: {}", createdCar);

        CreatedCarResponse carResponse =
                this.modelMapperService.forResponse()
                        .map(createdCar, CreatedCarResponse.class);
        LOGGER.info("Created car response: {}", carResponse);

        assert carResponse.getModelId() == carRequest.getModelId();
        //assert carResponse.getModelName().equals(model.getName());
        //assert carResponse.getCreatedDate().equals(car.getCreatedDate());

        return carResponse;
    }

    @Override
    public List<GetAllCarResponse> getAllCars() {
        List<Car> cars = carRepository.findAll();
        /*List<GetAllCarResponse> carResponses = cars.stream()
                .map(car -> modelMapperService.forResponse()
                        .map(car, GetAllCarResponse.class)).toList();*/
        List<GetAllCarResponse> carResponses = new ArrayList<>();
        for (Car car : cars) {
            GetAllCarResponse carResponse = new GetAllCarResponse();
            carResponse.setId(car.getId());
            carResponse.setModelYear(car.getModelYear());
            carResponse.setPlate(car.getPlate());
            carResponse.setDailyPrice(car.getDailyPrice());
            carResponse.setModelId(car.getModel().getId());
            carResponse.setModelName(car.getModel().getName());
            carResponse.setBrandId(car.getModel().getBrand().getId());
            carResponse.setBrandName(car.getModel().getBrand().getName());
            carResponse.setFuelId(car.getModel().getFuel().getId());
            carResponse.setFuelName(car.getModel().getFuel().getName());
            carResponse.setTransmissionId(car.getModel().getTransmission().getId());
            carResponse.setTransmissionName(car.getModel().getTransmission().getName());
            carResponses.add(carResponse);
        }
        return carResponses;
    }

    @Override
    public GetCarByIdResponse getCarById(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no car for this id"));
        GetCarByIdResponse carResponse = modelMapperService.forResponse()
                .map(car, GetCarByIdResponse.class);
        return carResponse;
    }

    @Override
    public UpdateCarResponse updateCarById(UpdateCarRequest carRequest, int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no car for this id"));
        Car updatedCar = modelMapperService.forRequest()
                .map(carRequest, Car.class);

        car.setId(id);
        car.setUpdatedDate(LocalDateTime.now());

        car.setPlate(updatedCar.getPlate());
        car.setState(updatedCar.getState());
        car.setDailyPrice(updatedCar.getDailyPrice());

        carRepository.save(car);

        UpdateCarResponse carResponse = modelMapperService
                .forResponse().map(car, UpdateCarResponse.class);

        return carResponse;
    }

    @Override
    public void deleteCarById(int id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no car for this id"));
        car.setDeletedDate(LocalDateTime.now());
        carRepository.deleteById(id);

    }
}