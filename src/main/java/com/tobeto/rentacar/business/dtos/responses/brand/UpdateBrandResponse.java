package com.tobeto.rentacar.business.dtos.responses.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandResponse {
    private int id;
    private String name;
}
