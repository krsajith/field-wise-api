package com.unnathy.fieldwise.service;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.dto.OrderItemDTO;
import com.unnathy.fieldwise.entity.OrderItem;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.repo.OrderItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService implements BasicEntityService<OrderItemDTO, Long> {

    private final OrderItemRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public OrderItemDTO post(OrderItemDTO data, String authorization, User principal) throws UnnathyError {
        OrderItem entity = modelMapperService.map(data, OrderItem.class);
        OrderItem saved = repository.save(entity);
        return modelMapperService.map(saved, OrderItemDTO.class);
    }

    @Override
    public OrderItemDTO patch(OrderItemDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public OrderItemDTO put(OrderItemDTO data, String authorization, User principal) throws UnnathyError {
        OrderItem entity = modelMapperService.map(data, OrderItem.class);
        OrderItem saved = repository.save(entity);
        return modelMapperService.map(saved, OrderItemDTO.class);
    }

    @Override
    public List<OrderItemDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, OrderItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, OrderItemDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "OrderItem not found", null));
    }
}
