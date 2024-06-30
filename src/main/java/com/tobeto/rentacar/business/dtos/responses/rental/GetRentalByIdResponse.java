package com.tobeto.rentacar.business.dtos.responses.rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRentalByIdResponse {
    private int id;
    private int carId;
    private int userId;
    private double totalCost;
}
