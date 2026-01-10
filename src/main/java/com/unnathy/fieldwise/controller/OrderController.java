package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.OrderDTO;
import com.unnathy.fieldwise.service.OrderService;
import lombok.RequiredArgsConstructor;
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
}
