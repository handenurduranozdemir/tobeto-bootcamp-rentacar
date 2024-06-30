package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.RentalService;
import com.tobeto.rentacar.business.dtos.request.rental.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.request.rental.UpdateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.rental.CreatedRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetAllRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetRentalByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.UpdateRentalResponse;
import com.tobeto.rentacar.business.rules.RentalBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.RentalRepository;
import com.tobeto.rentacar.entities.conretes.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapperService modelMapperService;
    private final RentalBusinessRules rentalBusinessRules;


    @Override
    public CreatedRentalResponse createRental(CreateRentalRequest createRentalRequest) {
        rentalBusinessRules.validateRentalRequest(createRentalRequest);
        Rental rental = this.modelMapperService.forRequest()
                .map(createRentalRequest, Rental.class);
        rental.setCreatedDate(LocalDateTime.now());

        Rental createdRental = this.rentalRepository.save(rental);
        CreatedRentalResponse rentalResponse = this.modelMapperService.forResponse()
                .map(createdRental,CreatedRentalResponse.class);
        return rentalResponse;
    }

    @Override
    public List<GetAllRentalResponse> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetAllRentalResponse> rentalResponses = rentals.stream()
                .map(rental -> modelMapperService.forResponse()
                        .map(rental, GetAllRentalResponse.class)).toList();

        return rentalResponses;
    }

    @Override
    public GetRentalByIdResponse getRentalById(int id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no rental this id"));
        GetRentalByIdResponse rentalResponse = modelMapperService.forResponse()
                .map(rental, GetRentalByIdResponse.class);

        return rentalResponse;
    }

    @Override
    public UpdateRentalResponse updateRentalById(UpdateRentalRequest updateRentalRequest, int id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no rental for this id"));
        Rental updatedRental = modelMapperService.forRequest()
                .map(updateRentalRequest, Rental.class);

        rental.setId(id);
        rental.setUpdatedDate(LocalDateTime.now());
        rental.setStartDate(updatedRental.getStartDate());
        rental.setEndDate(updatedRental.getEndDate());
        rental.setTotalCost(updatedRental.getTotalCost());

        rentalRepository.save(rental);

        UpdateRentalResponse rentalResponse = modelMapperService.forResponse()
                .map(rental, UpdateRentalResponse.class);

        return rentalResponse;
    }

    @Override
    public void deleteRentalById(int id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no rental for this id"));
        rental.setDeletedDate(LocalDateTime.now());

        rentalRepository.deleteById(id);
    }
}
