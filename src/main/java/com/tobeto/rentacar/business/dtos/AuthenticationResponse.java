package com.tobeto.rentacar.business.dtos;

import com.tobeto.rentacar.entities.conretes.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private UserRole userRole;
    private Long userId;
    private String jwt;
}
