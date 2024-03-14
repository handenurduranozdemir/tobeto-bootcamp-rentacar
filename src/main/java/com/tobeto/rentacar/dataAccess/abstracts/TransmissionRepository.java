package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.conretes.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionRepository extends JpaRepository<Transmission, Integer> {
}
