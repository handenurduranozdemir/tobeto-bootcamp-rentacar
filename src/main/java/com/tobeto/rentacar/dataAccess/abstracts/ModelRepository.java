package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.conretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {
}
