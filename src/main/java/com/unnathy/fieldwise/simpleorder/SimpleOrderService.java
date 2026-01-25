package com.unnathy.fieldwise.simpleorder;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.simpleorder.SimpleOrderDTO;
import com.unnathy.fieldwise.simpleorder.SimpleOrder;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.simpleorder.SimpleOrderRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleOrderService implements BasicEntityService<SimpleOrderDTO, SimpleOrderDTO, Long> {

    private final SimpleOrderRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public SimpleOrderDTO post(SimpleOrderDTO data, String authorization, User principal) throws UnnathyError {
        if(data.getOrderDate()==null) data.setOrderDate(LocalDate.now());
        data.setUserId(principal.getId());
        SimpleOrder entity = modelMapperService.map(data, SimpleOrder.class);
        SimpleOrder saved = repository.save(entity);
        return modelMapperService.map(saved, SimpleOrderDTO.class);
    }

    @Override
    public SimpleOrderDTO patch(SimpleOrderDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public SimpleOrderDTO put(SimpleOrderDTO data, String authorization, User principal) throws UnnathyError {
        SimpleOrder entity = modelMapperService.map(data, SimpleOrder.class);
        SimpleOrder saved = repository.save(entity);
        return modelMapperService.map(saved, SimpleOrderDTO.class);
    }

    @Override
    public List<SimpleOrderDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, SimpleOrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SimpleOrderDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, SimpleOrderDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "SimpleOrder not found", null));
    }
}


