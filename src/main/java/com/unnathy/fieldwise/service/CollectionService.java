package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.CollectionDTO;
import com.unnathy.fieldwise.entity.Collection;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.repo.CollectionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionService implements BasicEntityService<CollectionDTO, Long> {

    private final CollectionRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public CollectionDTO post(CollectionDTO data, String authorization, User principal) throws UnnathyError {
        Collection entity = modelMapperService.map(data, Collection.class);
        Collection saved = repository.save(entity);
        return modelMapperService.map(saved, CollectionDTO.class);
    }

    @Override
    public CollectionDTO patch(CollectionDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public CollectionDTO put(CollectionDTO data, String authorization, User principal) throws UnnathyError {
        Collection entity = modelMapperService.map(data, Collection.class);
        Collection saved = repository.save(entity);
        return modelMapperService.map(saved, CollectionDTO.class);
    }

    @Override
    public List<CollectionDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, CollectionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CollectionDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, CollectionDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Collection not found", null));
    }
}
