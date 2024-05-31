package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.core.utilities.exception.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.BrandRepository;
import com.tobeto.rentacar.entities.conretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class BrandBusinessRules {
    BrandRepository brandRepository;

    public void brandNameCanNotBeDuplicated (String brandName) {
        Optional<Brand> brand =brandRepository.findByNameIgnoreCase(brandName);
        if (brand.isPresent()) {
            throw new BusinessException("Brand exist!");
        }
    }

    public void isBrandExist(int brandId) {
        boolean isExist = brandRepository.existsById(brandId);

        if(!isExist) {
            throw new BusinessException("Brand does not exist!");
        }
    }

}
