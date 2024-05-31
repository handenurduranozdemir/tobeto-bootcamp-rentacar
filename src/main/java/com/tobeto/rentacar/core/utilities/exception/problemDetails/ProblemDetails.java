package com.tobeto.rentacar.core.utilities.exception.problemDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProblemDetails {

    private String title;
    private String detail;
    private String status;
    private String type;
}
