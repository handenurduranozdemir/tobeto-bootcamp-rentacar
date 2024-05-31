package com.tobeto.rentacar.business.dtos.responses.transmission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTransmissionByIdResponse {
    private int id;
    private String name;
}
