package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.business.dtos.request.rental.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.request.rental.UpdateRentalRequest;
import com.tobeto.rentacar.entities.conretes.Rental;
import com.tobeto.rentacar.dataAccess.abstracts.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository rentalRepository;

    public void validateRentalRequest(CreateRentalRequest rentalRequest) {
        checkIfCarIsAvailable(rentalRequest.getCarId(), rentalRequest.getStartDate(), rentalRequest.getEndDate());
        checkIfRentalPeriodIsValid(rentalRequest.getStartDate(), rentalRequest.getEndDate());
    }

    public void validateRentalUpdateRequest(UpdateRentalRequest rentalRequest, int rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("There is no rental for this id"));
        checkIfCarIsAvailable(rental.getCar().getId(), rentalRequest.getStartDate(), rentalRequest.getEndDate(), rentalId);
        checkIfRentalPeriodIsValid(rentalRequest.getStartDate(), rentalRequest.getEndDate());
    }

    private void checkIfCarIsAvailable(int carId, LocalDate startDate, LocalDate endDate) {
        List<Rental> conflictingRentals = rentalRepository.findConflictingRentals(carId, endDate, startDate);
        if (!conflictingRentals.isEmpty()) {
            throw new RuntimeException("The car is already rented for the specified dates");
        }
    }

    private void checkIfCarIsAvailable(int carId, LocalDate startDate, LocalDate endDate, int rentalId) {
        List<Rental> conflictingRentals = rentalRepository.findConflictingRentalsExcludingId(carId, endDate, startDate, rentalId);
        if (!conflictingRentals.isEmpty()) {
            throw new RuntimeException("The car is already rented for the specified dates");
        }
    }

    private void checkIfRentalPeriodIsValid(LocalDate startDate, LocalDate endDate) {
        long rentalPeriod = Duration.between(startDate, endDate).toDays();
        if (rentalPeriod < 3) {
            throw new RuntimeException("Rental period must be at least 3 days");
        }
    }
}
