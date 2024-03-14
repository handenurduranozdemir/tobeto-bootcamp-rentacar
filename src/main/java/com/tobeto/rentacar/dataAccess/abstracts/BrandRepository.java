package com.tobeto.rentacar.dataAccess.abstracts;

import com.tobeto.rentacar.entities.conretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
