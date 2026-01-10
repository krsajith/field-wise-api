package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.ShopDTO;
import com.unnathy.fieldwise.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shops")
@RequiredArgsConstructor
public class ShopController implements BaseController<ShopDTO, Long> {

    private final ShopService service;

    @Override
    public BasicEntityService<ShopDTO, Long> getService() {
        return service;
    }
}
