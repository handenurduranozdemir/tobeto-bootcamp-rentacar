package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.conretes.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelRepository extends JpaRepository<Fuel, Integer> {
}
