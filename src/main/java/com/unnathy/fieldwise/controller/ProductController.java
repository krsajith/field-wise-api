package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.ProductDTO;
import com.unnathy.fieldwise.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController implements BaseController<ProductDTO, Long> {

    private final ProductService service;

    @Override
    public BasicEntityService<ProductDTO, Long> getService() {
        return service;
    }
}
