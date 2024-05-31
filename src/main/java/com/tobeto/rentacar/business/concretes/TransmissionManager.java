package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.TransmissionService;
import com.tobeto.rentacar.business.dtos.request.transmission.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.request.transmission.UpdateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.transmission.CreatedTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.GetAllTransmissionResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.GetTransmissionByIdResponse;
import com.tobeto.rentacar.business.dtos.responses.transmission.UpdateTransmissionResponse;
import com.tobeto.rentacar.business.rules.TransmissionBusinessRules;
import com.tobeto.rentacar.core.utilities.mapping.ModelMapperService;
import com.tobeto.rentacar.dataAccess.abstracts.TransmissionRepository;
import com.tobeto.rentacar.entities.conretes.Transmission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TransmissionManager implements TransmissionService {
    private TransmissionRepository transmissionRepository;
    private ModelMapperService modelMapperService;
    private TransmissionBusinessRules transmissionBusinessRules;
    @Override
    public CreatedTransmissionResponse createTransmission(CreateTransmissionRequest transmissionRequest) {
        transmissionBusinessRules.transmissionNameCanNotBeDuplicated(transmissionRequest.getName());

        Transmission transmission = this.modelMapperService.forRequest().map(transmissionRequest, Transmission.class);
        transmission.setCreatedDate(LocalDateTime.now());
        Transmission createdTransmission = this.transmissionRepository.save(transmission);
        CreatedTransmissionResponse transmissionResponse =
                this.modelMapperService.forResponse().map(createdTransmission, CreatedTransmissionResponse.class);
        return transmissionResponse;
    }

    @Override
    public List<GetAllTransmissionResponse> getAllTransmissions() {
        List<Transmission> transmissions = transmissionRepository.findAll();
        List<GetAllTransmissionResponse> transmissionResponses = transmissions.stream()
                .map(transmission -> modelMapperService.forResponse()
                .map(transmission, GetAllTransmissionResponse.class)).toList();
        return transmissionResponses;
    }

    @Override
    public GetTransmissionByIdResponse getTransmissionById(int id) {
        Transmission transmission = transmissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no transmission for this id."));
        GetTransmissionByIdResponse transmissionResponse = modelMapperService.forResponse()
                .map(transmission, GetTransmissionByIdResponse.class);
        return transmissionResponse;
    }

    @Override
    public UpdateTransmissionResponse updateTransmissionById(UpdateTransmissionRequest transmissionRequest, int id) {
        Transmission transmission =transmissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no transmission for this id."));
        Transmission updatedTransmission = modelMapperService.forRequest()
                .map(transmissionRequest, Transmission.class);

        transmission.setId(id);
        transmission.setUpdatedDate(LocalDateTime.now());
        transmission.setName(updatedTransmission.getName());

        transmissionRepository.save(transmission);

        UpdateTransmissionResponse transmissionResponse = modelMapperService.forResponse()
                .map(transmission, UpdateTransmissionResponse.class);
        return transmissionResponse;
    }
}