package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.ShopVisitDTO;
import com.unnathy.fieldwise.service.ShopVisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shopVisits")
@RequiredArgsConstructor
public class ShopVisitController implements BaseController<ShopVisitDTO, Long> {

    private final ShopVisitService service;

    @Override
    public BasicEntityService<ShopVisitDTO, Long> getService() {
        return service;
    }
}
