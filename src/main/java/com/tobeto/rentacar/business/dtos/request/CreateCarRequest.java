package com.tobeto.rentacar.business.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCarRequest {
    @NotNull
    private int modelYear;
    @NotNull
    @Size(min=7, max=8)
    private String plate;
    @NotNull
    private int state;
    @NotNull
    private double dailyPrice;
}
