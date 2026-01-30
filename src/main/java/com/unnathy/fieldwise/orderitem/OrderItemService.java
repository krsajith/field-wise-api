package com.unnathy.fieldwise.orderitem;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.order.OrderDTO;
import com.unnathy.fieldwise.order.OrderService;
import com.unnathy.fieldwise.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class OrderItemService implements BasicEntityService<OrderItemDTO, OrderItemDTO, Long> {

    private final OrderItemRepository repository;
    private final ModelMapperService modelMapperService;
    private final OrderService orderService;
    private final ViewOrderItemRepository viewOrderItemRepository;
    
    @Override
    public OrderItemDTO post(OrderItemDTO data, String authorization, User principal) throws UnnathyError {
        applyLineTotal(data);
        if (data.getOrderId() == null) {
            if (principal == null) {
                throw new UnnathyError("UNAUTHORIZED", "User not authenticated", null);
            }
            OrderDTO orderDTO = buildOrderFromItem(data, principal);
            OrderDTO createdOrder = orderService.post(orderDTO, authorization, principal);
            data.setOrderId(createdOrder.getId());
        }

        // Check if order item already exists with same orderId and productId
        return repository.findByOrderIdAndProductId(data.getOrderId(), data.getProductId())
            .map(existingItem -> {
                // Update existing item: add quantities
                BigDecimal newQuantity = existingItem.getQuantity().add(data.getQuantity());
                existingItem.setQuantity(newQuantity);

                // Recalculate line total based on new quantity
                existingItem.setLineTotal(newQuantity.multiply(existingItem.getUnitPrice()));

                // Update other fields if provided
                if (data.getTaxRate() != null) {
                    existingItem.setTaxRate(data.getTaxRate());
                }
                if (data.getTaxAmount() != null) {
                    existingItem.setTaxAmount(data.getTaxAmount());
                }
                if (data.getDiscountPercentage() != null) {
                    existingItem.setDiscountPercentage(data.getDiscountPercentage());
                }
                if (data.getDiscountAmount() != null) {
                    existingItem.setDiscountAmount(data.getDiscountAmount());
                }

                OrderItem saved = repository.save(existingItem);
                return modelMapperService.map(saved, OrderItemDTO.class);
            })
            .orElseGet(() -> {
                // Insert new order item
                OrderItem entity = modelMapperService.map(data, OrderItem.class);
                OrderItem saved = repository.save(entity);
                return modelMapperService.map(saved, OrderItemDTO.class);
            });
    }

    @Override
    public OrderItemDTO patch(OrderItemDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public OrderItemDTO put(OrderItemDTO data, String authorization, User principal) throws UnnathyError {
        applyLineTotal(data);
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


    public List<ViewOrderItemDTO> getOrderItemView(String authorization, User principal) throws UnnathyError {
        return viewOrderItemRepository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, ViewOrderItemDTO.class))
                .collect(Collectors.toList());
    }

    public List<ViewOrderItemDTO> getOrderItemViewByOrderId(Long orderId) {
        return viewOrderItemRepository.findAllByOrderId(orderId).stream()
                .map(entity -> modelMapperService.map(entity, ViewOrderItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, OrderItemDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "OrderItem not found", null));
    }

    private OrderDTO buildOrderFromItem(OrderItemDTO data, User principal) {
        OrderDTO order = new OrderDTO();
        order.setOrderNumber("ORD-" + System.currentTimeMillis());
        order.setShopId(data.getShopId());
        order.setUserId(principal.getId());
        order.setShopVisitId(data.getShopVisitId());
        order.setOrderDate(LocalDate.now(ZoneOffset.UTC));

        BigDecimal subtotal = calculateSubtotal(data);
        BigDecimal taxAmount = getOrZero(data.getTaxAmount());
        BigDecimal discountAmount = getOrZero(data.getDiscountAmount());
        BigDecimal totalAmount = data.getLineTotal();
        if (totalAmount == null) {
            totalAmount = subtotal.add(taxAmount).subtract(discountAmount);
        }

        order.setSubtotal(subtotal);
        order.setTaxAmount(taxAmount);
        order.setDiscountAmount(discountAmount);
        order.setTotalAmount(totalAmount);
        order.setStatus("PENDING");
        return order;
    }

    private BigDecimal calculateSubtotal(OrderItemDTO data) {
        if (data.getQuantity() != null && data.getUnitPrice() != null) {
            return data.getQuantity().multiply(data.getUnitPrice());
        }
        if (data.getLineTotal() != null) {
            return data.getLineTotal();
        }
        return BigDecimal.ZERO;
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


