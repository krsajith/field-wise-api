package com.unnathy.fieldwise.order;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.order.OrderDTO;
import com.unnathy.fieldwise.orderview.OrderViewDTO;
import com.unnathy.fieldwise.order.Order;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.order.OrderRepository;
import com.unnathy.fieldwise.orderview.OrderViewRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class OrderService implements BasicEntityService<OrderDTO, Long> {

    private final OrderRepository repository;
    private final OrderViewRepository viewRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public OrderDTO post(OrderDTO data, String authorization, User principal) throws UnnathyError {
        Order entity = modelMapperService.map(data, Order.class);
        Order saved = repository.save(entity);
        return modelMapperService.map(saved, OrderDTO.class);
    }

    @Override
    public OrderDTO patch(OrderDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public OrderDTO put(OrderDTO data, String authorization, User principal) throws UnnathyError {
        Order entity = modelMapperService.map(data, Order.class);
        Order saved = repository.save(entity);
        return modelMapperService.map(saved, OrderDTO.class);
    }

    @Override
    public List<OrderDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, OrderDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Order not found", null));
    }

    public List<OrderViewDTO> getOrderView() {
        return viewRepository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, OrderViewDTO.class))
                .collect(Collectors.toList());
    }

    public List<OrderViewDTO> getOrderViewByUserId(Long userId) {
        return viewRepository.findAllByUserId(userId).stream()
                .map(entity -> modelMapperService.map(entity, OrderViewDTO.class))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getOrdersByStatus(String status) {
        return repository.findAllByStatusOrderByUpdatedAtDesc(status).stream()
                .map(entity -> modelMapperService.map(entity, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersByUpdatedAtDesc() {
        return repository.findAllByOrderByUpdatedAtDesc().stream()
                .map(entity -> modelMapperService.map(entity, OrderDTO.class))
                .collect(Collectors.toList());
    }
}
