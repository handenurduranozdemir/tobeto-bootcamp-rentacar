package com.tobeto.rentacar.business.dtos.request.car;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCarRequest {
    @NotNull
    @Min(value = 1950)
    @Max(value = 2024)
    private int modelYear;
    @NotNull
    @Size(min=7, max=8)
    private String plate;
    @NotNull
    @Min(value = 1)
    @Max(value = 3)
    private int state;
    @NotBlank
    @Positive
    private double dailyPrice;
    @NotNull
    private int modelId;
}
