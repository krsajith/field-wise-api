package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.TransportationModeDTO;
import com.unnathy.fieldwise.entity.TransportationMode;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.repo.TransportationModeRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransportationModeService implements BasicEntityService<TransportationModeDTO, Long> {

    private final TransportationModeRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public TransportationModeDTO post(TransportationModeDTO data, String authorization, User principal) throws UnnathyError {
        TransportationMode entity = modelMapperService.map(data, TransportationMode.class);
        TransportationMode saved = repository.save(entity);
        return modelMapperService.map(saved, TransportationModeDTO.class);
    }

    @Override
    public TransportationModeDTO patch(TransportationModeDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public TransportationModeDTO put(TransportationModeDTO data, String authorization, User principal) throws UnnathyError {
        TransportationMode entity = modelMapperService.map(data, TransportationMode.class);
        TransportationMode saved = repository.save(entity);
        return modelMapperService.map(saved, TransportationModeDTO.class);
    }

    @Override
    public List<TransportationModeDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, TransportationModeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TransportationModeDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, TransportationModeDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "TransportationMode not found", null));
    }
}
