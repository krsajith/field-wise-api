package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.ProductCategoryDTO;
import com.unnathy.fieldwise.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productCategorys")
@RequiredArgsConstructor
public class ProductCategoryController implements BaseController<ProductCategoryDTO, Long> {

    private final ProductCategoryService service;

    @Override
    public BasicEntityService<ProductCategoryDTO, Long> getService() {
        return service;
    }
}
