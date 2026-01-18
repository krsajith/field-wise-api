package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.OrderDTO;
import com.unnathy.fieldwise.dto.OrderViewDTO;
import com.unnathy.fieldwise.entity.User;
import com.unnathy.fieldwise.service.OrderService;
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
            @PathVariable("userId") Long userId) {
        return service.getOrderViewByUserId(userId);
    }
}
