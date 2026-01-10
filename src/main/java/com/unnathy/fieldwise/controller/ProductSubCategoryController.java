package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.ProductSubCategoryDTO;
import com.unnathy.fieldwise.service.ProductSubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productSubCategorys")
@RequiredArgsConstructor
public class ProductSubCategoryController implements BaseController<ProductSubCategoryDTO, Long> {

    private final ProductSubCategoryService service;

    @Override
    public BasicEntityService<ProductSubCategoryDTO, Long> getService() {
        return service;
    }
}
