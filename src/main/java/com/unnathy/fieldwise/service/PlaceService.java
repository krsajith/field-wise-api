package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.PlaceDTO;
import com.unnathy.fieldwise.entity.Place;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.repo.PlaceRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceService implements BasicEntityService<PlaceDTO, Long> {

    private final PlaceRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public PlaceDTO post(PlaceDTO data, String authorization, User principal) throws UnnathyError {
        Place entity = modelMapperService.map(data, Place.class);
        Place saved = repository.save(entity);
        return modelMapperService.map(saved, PlaceDTO.class);
    }

    @Override
    public PlaceDTO patch(PlaceDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public PlaceDTO put(PlaceDTO data, String authorization, User principal) throws UnnathyError {
        Place entity = modelMapperService.map(data, Place.class);
        Place saved = repository.save(entity);
        return modelMapperService.map(saved, PlaceDTO.class);
    }

    @Override
    public List<PlaceDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, PlaceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PlaceDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, PlaceDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Place not found", null));
    }
}
