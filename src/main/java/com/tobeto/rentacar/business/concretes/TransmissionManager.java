package com.tobeto.rentacar.business.concretes;

import com.tobeto.rentacar.business.abstracts.TransmissionService;
import com.tobeto.rentacar.business.dtos.request.CreateTransmissionRequest;
import com.tobeto.rentacar.business.dtos.responses.*;
import com.tobeto.rentacar.core.mapping.ModelMapperService;
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
    @Override
    public CreatedTransmissionResponse add(CreateTransmissionRequest createTransmissionRequest) {
        Transmission transmission = this.modelMapperService.forRequest().map(createTransmissionRequest, Transmission.class);
        transmission.setCreatedDate(LocalDateTime.now());
        Transmission createdTransmission = this.transmissionRepository.save(transmission);
        CreatedTransmissionResponse createdTransmissionResponse =
                this.modelMapperService.forResponse().map(createdTransmission, CreatedTransmissionResponse.class);
        return createdTransmissionResponse;
    }

    @Override
    public List<GetAllTransmissionResponse> getAll() {
        List<Transmission> transmissions = transmissionRepository.findAll();
        List<GetAllTransmissionResponse> transmissionResponses = transmissions.stream()
                .map(transmission -> modelMapperService.forResponse()
                .map(transmission, GetAllTransmissionResponse.class)).toList();
        return transmissionResponses;
    }
}