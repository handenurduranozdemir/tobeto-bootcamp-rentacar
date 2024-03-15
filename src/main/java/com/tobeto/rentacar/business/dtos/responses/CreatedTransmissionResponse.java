package com.tobeto.rentacar.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedTransmissionResponse {
    private int id;
    private String name;
}
