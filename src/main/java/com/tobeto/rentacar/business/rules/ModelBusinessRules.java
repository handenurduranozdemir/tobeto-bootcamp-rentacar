package com.tobeto.rentacar.business.rules;

import com.tobeto.rentacar.core.utilities.exception.types.BusinessException;
import com.tobeto.rentacar.dataAccess.abstracts.ModelRepository;
import com.tobeto.rentacar.entities.conretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ModelBusinessRules {
    ModelRepository modelRepository;

    public void modelNameCanNotBeDuplicated(String modelName) {
        Optional<Model> model = modelRepository.findByNameIgnoreCase(modelName);

        if(model.isPresent()) {
            throw new BusinessException("Model exists!");
        }
    }

    public void isModelExist(int modelId) {
        boolean isExist = modelRepository.existsById(modelId);

        if(!isExist) {
            throw new BusinessException("Model does not exist!");
        }
    }
}
