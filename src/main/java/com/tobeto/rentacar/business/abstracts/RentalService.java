package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.api.controllers.RentalController;
import com.tobeto.rentacar.business.dtos.request.rental.CreateRentalRequest;
import com.tobeto.rentacar.business.dtos.request.rental.UpdateRentalRequest;
import com.tobeto.rentacar.business.dtos.responses.rental.CreatedRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetAllRentalResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.GetRentalByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.rental.UpdateRentalResponse;

import java.util.List;

public interface RentalService {
    CreatedRentalResponse createRental(CreateRentalRequest createRentalRequest);
    List<GetAllRentalResponse> getAllRentals();
    GetRentalByIdResponse getRentalById(int id);
    UpdateRentalResponse updateRentalById(UpdateRentalRequest updateRentalRequest, int id);
    void deleteRentalById(int id);

}
