package com.unnathy.fieldwise.order;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.orderview.OrderViewDTO;
import com.unnathy.fieldwise.orderview.OrderViewRepository;
import com.unnathy.fieldwise.orderitem.OrderItemDTO;
import com.unnathy.fieldwise.orderitem.OrderItemRepository;
import com.unnathy.fieldwise.orderitem.OrderItemService;
import com.unnathy.fieldwise.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements BasicEntityService<OrderDTO, OrderDTO, Long> {

    private final OrderRepository repository;
    private final OrderViewRepository viewRepository;
    private final ModelMapperService modelMapperService;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemService orderItemService;

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

    public Page<OrderViewDTO> getOrderView(int page, int size) {
        return viewRepository.findAll(PageRequest.of(page, size, Sort.by("updatedAt").descending()))
                .map(entity -> modelMapperService.map(entity, OrderViewDTO.class));
    }

    public Page<OrderViewDTO> getOrderViewByUserId(Long userId, int page, int size) {
        return viewRepository.findAllByUserId(userId, PageRequest.of(page, size, Sort.by("updatedAt").descending()))
                .map(entity -> modelMapperService.map(entity, OrderViewDTO.class));
    }

    public OrderViewDTO getOrderViewById(Long id) throws UnnathyError {
        return viewRepository.findById(id)
                .map(entity -> modelMapperService.map(entity, OrderViewDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Order view not found", null));
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

    @Transactional
    public OrderDTO postWithItems(OrderWithItemsDTO payload, String authorization, User principal) throws UnnathyError {
        if (payload == null || payload.getOrder() == null) {
            throw new UnnathyError("INVALID_REQUEST", "Order payload is required", null);
        }
        OrderDTO order = payload.getOrder();
        if (order.getOrderNumber() == null || order.getOrderNumber().isBlank()) {
            order.setOrderNumber("ORD-" + System.currentTimeMillis());
        }
        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDate.now(ZoneOffset.UTC));
        }
        if (order.getStatus() == null || order.getStatus().isBlank()) {
            order.setStatus("PENDING");
        }
        List<OrderItemDTO> items = payload.getItems();
        normalizeTotals(order, items);
        Order entity = modelMapperService.map(order, Order.class);
        Order saved = repository.save(entity);
        saveItems(saved.getId(), items, authorization, principal);
        return modelMapperService.map(saved, OrderDTO.class);
    }

    @Transactional
    public OrderDTO putWithItems(OrderWithItemsDTO payload, String authorization, User principal) throws UnnathyError {
        if (payload == null || payload.getOrder() == null) {
            throw new UnnathyError("INVALID_REQUEST", "Order payload is required", null);
        }
        OrderDTO order = payload.getOrder();
        if (order.getId() == null) {
            throw new UnnathyError("INVALID_REQUEST", "Order id is required for update", null);
        }
        Order existing = repository.findById(order.getId())
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Order not found", null));

        if (order.getOrderNumber() == null || order.getOrderNumber().isBlank()) {
            order.setOrderNumber(existing.getOrderNumber());
        }
        if (order.getOrderDate() == null) {
            order.setOrderDate(existing.getOrderDate());
        }
        if (order.getStatus() == null || order.getStatus().isBlank()) {
            order.setStatus(existing.getStatus());
        }
        List<OrderItemDTO> items = payload.getItems();
        normalizeTotals(order, items);
        Order entity = modelMapperService.map(order, Order.class);
        Order saved = repository.save(entity);
        orderItemRepository.deleteByOrderId(saved.getId());
        saveItems(saved.getId(), items, authorization, principal);
        return modelMapperService.map(saved, OrderDTO.class);
    }

    private void saveItems(Long orderId, List<OrderItemDTO> items, String authorization, User principal) throws UnnathyError {
        if (items == null || items.isEmpty()) {
            return;
        }
        // Loop through each item and use OrderItemService.post()
        // This automatically handles duplicate detection (same orderId + productId)
        for (OrderItemDTO item : items) {
            item.setOrderId(orderId);
            orderItemService.post(item, authorization, principal);
        }
    }

    private void normalizeTotals(OrderDTO order, List<OrderItemDTO> items) {
        BigDecimal subtotal = order.getSubtotal();
        BigDecimal taxAmount = order.getTaxAmount();
        BigDecimal discountAmount = order.getDiscountAmount();

        BigDecimal computedSubtotal = BigDecimal.ZERO;
        BigDecimal computedTax = BigDecimal.ZERO;
        BigDecimal computedDiscount = BigDecimal.ZERO;
        if (items != null) {
            for (OrderItemDTO item : items) {
                applyLineTotal(item);
                computedSubtotal = computedSubtotal.add(getOrZero(item.getLineTotal()));
                computedTax = computedTax.add(getOrZero(item.getTaxAmount()));
                computedDiscount = computedDiscount.add(getOrZero(item.getDiscountAmount()));
            }
        }

        if (subtotal == null) {
            subtotal = computedSubtotal;
        }
        if (taxAmount == null) {
            taxAmount = computedTax;
        }
        if (discountAmount == null) {
            discountAmount = computedDiscount;
        }
        BigDecimal totalAmount = order.getTotalAmount();
        if (totalAmount == null) {
            totalAmount = subtotal.add(taxAmount).subtract(discountAmount);
        }

        order.setSubtotal(subtotal);
        order.setTaxAmount(taxAmount);
        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(totalAmount);
    }

    private BigDecimal getOrZero(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    private void applyLineTotal(OrderItemDTO data) {
        if (data.getQuantity() != null && data.getUnitPrice() != null) {
            data.setLineTotal(data.getQuantity().multiply(data.getUnitPrice()));
        }
    }
}
