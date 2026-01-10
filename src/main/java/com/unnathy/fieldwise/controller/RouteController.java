package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.RouteDTO;
import com.unnathy.fieldwise.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController implements BaseController<RouteDTO, Long> {

    private final RouteService service;

    @Override
    public BasicEntityService<RouteDTO, Long> getService() {
        return service;
    }
}
