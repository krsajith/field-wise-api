package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.DistrictDTO;
import com.unnathy.fieldwise.entity.District;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.repo.DistrictRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistrictService implements BasicEntityService<DistrictDTO, Long> {

    private final DistrictRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public DistrictDTO post(DistrictDTO data, String authorization, User principal) throws UnnathyError {
        District entity = modelMapperService.map(data, District.class);
        District saved = repository.save(entity);
        return modelMapperService.map(saved, DistrictDTO.class);
    }

    @Override
    public DistrictDTO patch(DistrictDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public DistrictDTO put(DistrictDTO data, String authorization, User principal) throws UnnathyError {
        District entity = modelMapperService.map(data, District.class);
        District saved = repository.save(entity);
        return modelMapperService.map(saved, DistrictDTO.class);
    }

    @Override
    public List<DistrictDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, DistrictDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DistrictDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, DistrictDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "District not found", null));
    }
}
