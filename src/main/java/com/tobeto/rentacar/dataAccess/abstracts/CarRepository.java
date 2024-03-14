package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.conretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
