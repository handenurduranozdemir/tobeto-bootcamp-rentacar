package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.BrandService;
import com.tobeto.rentacar.business.dtos.request.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllBrandResponse;
import com.tobeto.rentacar.core.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.BrandRepository;
import com.tobeto.rentacar.entities.conretes.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private BrandRepository brandRepository;
    private ModelMapperService modelMapperService;

    @Override
    public CreatedBrandResponse add(CreateBrandRequest createBrandRequest) {
        Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        brand.setCreatedDate(LocalDateTime.now());
        Brand createdBrand = this.brandRepository.save(brand);
        CreatedBrandResponse createdBrandResponse =
                this.modelMapperService.forResponse().map(createdBrand, CreatedBrandResponse.class);
        return createdBrandResponse;
    }

    @Override
    public List<GetAllBrandResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandResponse> brandResponses = brands.stream().map(brand -> modelMapperService.forResponse()
                .map(brand, GetAllBrandResponse.class)).toList();
        return brandResponses;
    }
}
