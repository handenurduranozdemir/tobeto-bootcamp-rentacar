package com.tobeto.rentacar.services;

import com.tobeto.rentacar.business.dtos.SignupRequest;
import com.tobeto.rentacar.business.dtos.UserDto;

public interface AuthService {
    UserDto createCustomer(SignupRequest signupRequest);
    boolean hasCustomerWithEmail(String email);
}
