package com.tobeto.rentacar.business.dtos.request.rental;

import com.tobeto.rentacar.entities.conretes.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
    private int carId;
    private int userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;
}
