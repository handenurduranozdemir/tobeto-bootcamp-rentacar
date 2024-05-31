package com.tobeto.rentacar.business.abstracts;



import com.tobeto.rentacar.business.dtos.request.transmission.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.request.transmission.UpdateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.transmission.CreatedTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.GetAllTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.GetTransmissionByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.UpdateTransmissionResponse;

import java.util.List;

public interface TransmissionService {
    CreatedTransmissionResponse createTransmission(CreateTransmissionRequest createTransmissionRequest);
    List<GetAllTransmissionResponse> getAllTransmissions();
    GetTransmissionByIdResponse getTransmissionById(int id);
    UpdateTransmissionResponse updateTransmissionById(UpdateTransmissionRequest updateTransmissionRequest, int id);
}