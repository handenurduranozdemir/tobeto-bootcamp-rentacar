package com.tobeto.rentacar.business.dtos.responses.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelByIdResponse {
    private int id;
    private String name;
    private int brandId;
    private int fuelId;
    private int transmissonId;
}
