package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.ExpenseCategoryDTO;
import com.unnathy.fieldwise.service.ExpenseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expenseCategorys")
@RequiredArgsConstructor
public class ExpenseCategoryController implements BaseController<ExpenseCategoryDTO, Long> {

    private final ExpenseCategoryService service;

    @Override
    public BasicEntityService<ExpenseCategoryDTO, Long> getService() {
        return service;
    }
}
