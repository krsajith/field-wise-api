package com.unnathy.fieldwise.orderitem;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.order.OrderDTO;
import com.unnathy.fieldwise.order.OrderService;
import com.unnathy.fieldwise.orderitem.OrderItemDTO;
import com.unnathy.fieldwise.orderitem.OrderItemService;
import com.unnathy.fieldwise.user.User;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/orderItems")
@RequiredArgsConstructor
public class OrderItemController implements BaseController<OrderItemDTO, OrderItemDTO, Long> {

    private final OrderItemService service;
    private final OrderService orderService;

    @Override
    public BasicEntityService<OrderItemDTO, OrderItemDTO, Long> getService() {
        return service;
    }

    @Override
    @PostMapping()
    public OrderItemDTO post(
            @RequestBody OrderItemDTO data,
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal) throws UnnathyError {

        // Auto-create order if orderId is not provided (mobile app convenience)
        if (data.getOrderId() == null) {
            if (principal == null) {
                throw new UnnathyError("UNAUTHORIZED", "User not authenticated", null);
            }
            // Build and create order from item data
            OrderDTO orderDTO = service.buildOrderFromItem(data, principal);
            OrderDTO createdOrder = orderService.post(orderDTO, authorization, principal);
            data.setOrderId(createdOrder.getId());
        }

        // Add the item (with duplicate detection handled by service)
        return service.post(data, authorization, principal);
    }

    @GetMapping("/view")
    public List<ViewOrderItemDTO> getOrderItemView(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal) throws UnnathyError {
        return service.getOrderItemView(authorization, principal);
    }

    @GetMapping("/view/order/{orderId}")
    public List<ViewOrderItemDTO> getOrderItemViewByOrderId(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal,
            @PathVariable Long orderId) {
        return service.getOrderItemViewByOrderId(orderId);
    }
}



