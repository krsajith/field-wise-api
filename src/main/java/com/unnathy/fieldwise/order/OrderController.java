package com.unnathy.fieldwise.order;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.orderview.OrderViewDTO;
import com.unnathy.fieldwise.user.User;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController implements BaseController<OrderDTO, Long> {

    private final OrderService service;

    @Override
    public BasicEntityService<OrderDTO, Long> getService() {
        return service;
    }

    @GetMapping("/view")
    public List<OrderViewDTO> getOrderView(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal) {
        return service.getOrderView();
    }

    @GetMapping("/view/user/{userId}")
    public List<OrderViewDTO> getOrderViewByUserId(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal,
            @PathVariable Long userId) {
        return service.getOrderViewByUserId(userId);
    }

    @GetMapping("/view/{id}")
    public OrderViewDTO getOrderViewById(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal,
            @PathVariable Long id) throws UnnathyError {
        return service.getOrderViewById(id);
    }

    @GetMapping("/status/{status}")
    public List<OrderDTO> getOrdersByStatus(
            @Parameter(hidden = true) @RequestHeader("Authorization") String authorization,
            @Parameter(hidden = true) @AuthenticationPrincipal User principal,
            @PathVariable String status) {
        return service.getOrdersByStatus(status);
    }
}
