package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.SimpleOrderDTO;
import com.unnathy.fieldwise.service.SimpleOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/simpleOrders")
@RequiredArgsConstructor
public class SimpleOrderController implements BaseController<SimpleOrderDTO, Long> {

    private final SimpleOrderService service;

    @Override
    public BasicEntityService<SimpleOrderDTO, Long> getService() {
        return service;
    }
}
