package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.dto.SimpleOrderViewDTO;
import com.unnathy.fieldwise.repo.SimpleOrderViewRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleOrderViewService {

    private final SimpleOrderViewRepository repository;
    private final ModelMapperService modelMapperService;

    public List<SimpleOrderViewDTO> getAll() {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, SimpleOrderViewDTO.class))
                .collect(Collectors.toList());
    }

    public List<SimpleOrderViewDTO> getByUserId(Long userId) {
        return repository.findAllByUserId(userId).stream()
                .map(entity -> modelMapperService.map(entity, SimpleOrderViewDTO.class))
                .collect(Collectors.toList());
    }
}
