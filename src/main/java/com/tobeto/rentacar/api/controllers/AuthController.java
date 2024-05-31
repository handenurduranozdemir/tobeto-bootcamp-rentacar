package com.tobeto.rentacar.api.controllers;

import com.tobeto.rentacar.business.dtos.AuthenticationRequest;
import com.tobeto.rentacar.business.dtos.AuthenticationResponse;
import com.tobeto.rentacar.business.dtos.SignupRequest;
import com.tobeto.rentacar.business.dtos.UserDto;
import com.tobeto.rentacar.core.utilities.jwt.JwtService;
import com.tobeto.rentacar.dataAccess.abstracts.UserRepository;
import com.tobeto.rentacar.entities.conretes.User;
import com.tobeto.rentacar.services.AuthService;
import com.tobeto.rentacar.services.jwt.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> createCustomer(@RequestBody SignupRequest signupRequest) {
        if (authService.hasCustomerWithEmail(signupRequest.getEmail()))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Email already exist.");

        UserDto createdUserDto = authService.createCustomer(signupRequest);
        if (createdUserDto == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BAD REQUEST");

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDto);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password.");
        }
        final UserDetails userDetails = userService.userDetailsService()
                .loadUserByUsername(authenticationRequest.getEmail());

        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

        final String jwt = jwtService.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }

        return authenticationResponse;
    }
}
