package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.abstracts.BrandService;
import com.tobeto.rentacar.business.dtos.request.brand.CreateBrandRequest;
import com.tobeto.rentacar.business.dtos.request.brand.UpdateBrandRequest;
import com.tobeto.rentacar.business.dtos.responses.brand.CreatedBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetAllBrandResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.GetBrandByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.brand.UpdateBrandResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/brands")
@CrossOrigin(origins = "http://localhost:4200")
public class BrandsController {
    private BrandService brandService;

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedBrandResponse createBrand(@Valid @RequestBody CreateBrandRequest brandRequest) {
        return brandService.createBrand(brandRequest);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllBrandResponse> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    public GetBrandByIdResponse getBrandById(@PathVariable int id){
        return brandService.getBrandById(id);
    }

    @PutMapping("/{id}")
    public UpdateBrandResponse updateBrandById(@RequestBody UpdateBrandRequest brandRequest, @PathVariable int id){
        return brandService.updateBrandById(brandRequest,id);
    }

    @DeleteMapping("/{id}")
    void deleteBrandById(@PathVariable int id){
        brandService.deleteBrandById(id);
    }
}