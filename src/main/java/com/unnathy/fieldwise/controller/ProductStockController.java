package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.ProductStockDTO;
import com.unnathy.fieldwise.service.ProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productStocks")
@RequiredArgsConstructor
public class ProductStockController implements BaseController<ProductStockDTO, Long> {

    private final ProductStockService service;

    @Override
    public BasicEntityService<ProductStockDTO, Long> getService() {
        return service;
    }
}
