package com.tobeto.rentacar.business.abstracts;

import com.tobeto.rentacar.business.dtos.request.model.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.request.model.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.model.CreatedModelResponse;
import com.tobeto.rentacar.business.dtos.responses.model.GetAllModelResponse;
import com.tobeto.rentacar.business.dtos.responses.model.GetModelByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.model.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    CreatedModelResponse createModel(CreateModelRequest createModelRequest);
    List<GetAllModelResponse> getAllModels();
    GetModelByIdResponse getModelById(int id);
    UpdateModelResponse updateModelById(UpdateModelRequest updateModelRequest, int id);
    void deleteModelById(int id);
}
