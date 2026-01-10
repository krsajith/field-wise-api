package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.LocationLogDTO;
import com.unnathy.fieldwise.entity.LocationLog;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.repo.LocationLogRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationLogService implements BasicEntityService<LocationLogDTO, Long> {

    private final LocationLogRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public LocationLogDTO post(LocationLogDTO data, String authorization, User principal) throws UnnathyError {
        LocationLog entity = modelMapperService.map(data, LocationLog.class);
        LocationLog saved = repository.save(entity);
        return modelMapperService.map(saved, LocationLogDTO.class);
    }

    @Override
    public LocationLogDTO patch(LocationLogDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public LocationLogDTO put(LocationLogDTO data, String authorization, User principal) throws UnnathyError {
        LocationLog entity = modelMapperService.map(data, LocationLog.class);
        LocationLog saved = repository.save(entity);
        return modelMapperService.map(saved, LocationLogDTO.class);
    }

    @Override
    public List<LocationLogDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, LocationLogDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LocationLogDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, LocationLogDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "LocationLog not found", null));
    }
}
