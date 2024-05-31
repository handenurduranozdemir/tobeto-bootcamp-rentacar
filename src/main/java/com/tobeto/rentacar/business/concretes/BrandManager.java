package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.BrandService;
import com.tobeto.rentacar.business.dtos.request.brand.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.request.brand.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.brand.CreatedBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetAllBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetBrandByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.UpdateBrandResponse;
import com.tobeto.rentacar.business.rules.BrandBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
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

    private BrandBusinessRules brandBusinessRules;
    @Override
    public CreatedBrandResponse createBrand(CreateBrandRequest brandRequest) {
        brandBusinessRules.brandNameCanNotBeDuplicated(brandRequest.getName());

        Brand brand = this.modelMapperService.forRequest().map(brandRequest, Brand.class);
        brand.setCreatedDate(LocalDateTime.now());
        Brand createdBrand = this.brandRepository.save(brand);
        CreatedBrandResponse brandResponse =
                this.modelMapperService.forResponse().map(createdBrand, CreatedBrandResponse.class);
        return brandResponse;
    }

    @Override
    public List<GetAllBrandResponse> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandResponse> brandResponses = brands.stream().map(brand -> modelMapperService.forResponse()
                .map(brand, GetAllBrandResponse.class)).toList();
        return brandResponses;
    }

    @Override
    public GetBrandByIdResponse getBrandById(int id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no brand for this id"));
        GetBrandByIdResponse brandResponse = modelMapperService.forResponse()
                .map(brand, GetBrandByIdResponse.class);
        return brandResponse;
    }

    @Override
    public UpdateBrandResponse updateBrandById(UpdateBrandRequest brandRequest, int id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no brand for this id"));
        Brand updatedBrand = modelMapperService.forRequest()
                .map(brandRequest, Brand.class);

        brand.setId(id);
        brand.setUpdatedDate(LocalDateTime.now());
        brand.setName(updatedBrand.getName() != null ? updatedBrand.getName() : brand.getName());

        brandRepository.save(brand);

        UpdateBrandResponse brandResponse = modelMapperService.forResponse()
                .map(brand, UpdateBrandResponse.class);
        return brandResponse;
    }

    @Override
    public void deleteBrandById(int id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Brand for this id"));
        brand.setDeletedDate(LocalDateTime.now());
        brandRepository.deleteById(id);

    }
}
