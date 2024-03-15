package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.request.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.CreatedModelResponse;
import com.tobeto.rentacar.business.dtos.responses.GetAllModelResponse;

import java.util.List;

public interface ModelService {
    CreatedModelResponse add(CreateModelRequest createModelRequest);
    List<GetAllModelResponse> getAll();
}
