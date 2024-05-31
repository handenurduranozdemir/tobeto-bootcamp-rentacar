package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.request.brand.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.request.brand.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.brand.CreatedBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetAllBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetBrandByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.UpdateBrandResponse;

import java.util.List;

public interface BrandService {
    CreatedBrandResponse createBrand(CreateBrandRequest createBrandRequest);
    List<GetAllBrandResponse> getAllBrands();
    GetBrandByIdResponse getBrandById(int id);
    UpdateBrandResponse updateBrandById(UpdateBrandRequest updateBrandRequest, int id);
    void deleteBrandById(int id);
}
