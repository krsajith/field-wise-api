package com.unnathy.fieldwise.productstock;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.productstock.ProductStockDTO;
import com.unnathy.fieldwise.productstock.ProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/productStocks")
@RequiredArgsConstructor
public class ProductStockController implements BaseController<ProductStockDTO, ProductStockDTO, Long> {

    private final ProductStockService service;

    @Override
    public BasicEntityService<ProductStockDTO, ProductStockDTO, Long> getService() {
        return service;
    }
}



