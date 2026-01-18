package com.unnathy.fieldwise.orderitem;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.orderitem.OrderItemDTO;
import com.unnathy.fieldwise.orderitem.OrderItemService;
import com.unnathy.fieldwise.user.User;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/orderItems")
@RequiredArgsConstructor
public class OrderItemController implements BaseController<OrderItemDTO, Long> {

    private final OrderItemService service;

    @Override
    public BasicEntityService<OrderItemDTO, Long> getService() {
        return service;
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
