package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.core.utilities.exception.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.CarRepository;
import com.tobeto.rentacar.entities.conretes.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CarBusinessRules {
    CarRepository carRepository;

    public void carPlateCanNotBeDuplicated(String carPlate) {
        Optional<Car> car = carRepository.findByPlate(carPlate);
        if(car.isPresent()) {
            throw new BusinessException("Car exists!");
        }
    }
}
