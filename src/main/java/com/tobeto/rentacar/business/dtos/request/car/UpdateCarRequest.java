package com.tobeto.rentacar.business.dtos.request.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
    private String plate;
    private int state;
    private double dailyPrice;
}
