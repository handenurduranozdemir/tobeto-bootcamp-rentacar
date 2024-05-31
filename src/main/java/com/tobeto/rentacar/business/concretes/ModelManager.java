package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.ModelService;
import com.tobeto.rentacar.business.dtos.request.model.CreateModelRequest;
import com.tobeto.rentacar.business.dtos.request.model.UpdateModelRequest;
import com.tobeto.rentacar.business.dtos.responses.model.CreatedModelResponse;
import com.tobeto.rentacar.business.dtos.responses.model.GetAllModelResponse;
import com.tobeto.rentacar.business.dtos.responses.model.GetModelByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.model.UpdateModelResponse;
import com.tobeto.rentacar.business.rules.BrandBusinessRules;
import com.tobeto.rentacar.business.rules.FuelBusinessRules;
import com.tobeto.rentacar.business.rules.ModelBusinessRules;
import com.tobeto.rentacar.business.rules.TransmissionBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.ModelRepository;
import com.tobeto.rentacar.entities.conretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;
    private ModelBusinessRules modelBusinessRules;
    private BrandBusinessRules brandBusinessRules;
    private FuelBusinessRules fuelBusinessRules;
    private TransmissionBusinessRules transmissionBusinessRules;

    @Override
    public CreatedModelResponse createModel(CreateModelRequest modelRequest) {
        modelBusinessRules.modelNameCanNotBeDuplicated(modelRequest.getName());
        brandBusinessRules.isBrandExist(modelRequest.getBrandId());
        fuelBusinessRules.isFuelExist(modelRequest.getFuelId());
        transmissionBusinessRules.isTransmissionExist(modelRequest.getTransmissionId());

        Model model = this.modelMapperService.forRequest().map(modelRequest, Model.class);
        model.setCreatedDate(LocalDateTime.now());
        Model createdModel = this.modelRepository.save(model);
        CreatedModelResponse modelResponse =
                this.modelMapperService.forResponse().map(createdModel, CreatedModelResponse.class);
        return modelResponse;
    }

    @Override
    public List<GetAllModelResponse> getAllModels() {
        List<Model> models = modelRepository.findAll();
        List<GetAllModelResponse> modelResponses = models.stream().map(model -> modelMapperService.forResponse()
                .map(model, GetAllModelResponse.class)).toList();
        return modelResponses;
    }

    @Override
    public GetModelByIdResponse getModelById(int id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Model for this id"));
        GetModelByIdResponse modelResponse= modelMapperService.forResponse()
                .map(model, GetModelByIdResponse.class);
        return modelResponse;
    }

    @Override
    public UpdateModelResponse updateModelById(UpdateModelRequest modelRequest, int id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Model for this id"));
        Model updatedModel = modelMapperService.forRequest()
                .map(modelRequest, Model.class);
        model.setId(id);
        model.setUpdatedDate(LocalDateTime.now());
        model.setName(updatedModel.getName() != null ? updatedModel.getName() : model.getName());

        modelRepository.save(model);

        UpdateModelResponse modelResponse = modelMapperService.forResponse()
                .map(model, UpdateModelResponse.class);
        return modelResponse;
    }

    @Override
    public void deleteModelById(int id) {
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no model for this id."));

        model.setDeletedDate(LocalDateTime.now());
        modelRepository.deleteById(id);
    }
}