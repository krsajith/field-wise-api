package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.OrderItemDTO;
import com.unnathy.fieldwise.service.OrderItemService;
import lombok.RequiredArgsConstructor;
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
}
