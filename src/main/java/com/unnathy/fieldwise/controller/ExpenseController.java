package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.ExpenseDTO;
import com.unnathy.fieldwise.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController implements BaseController<ExpenseDTO, Long> {

    private final ExpenseService service;

    @Override
    public BasicEntityService<ExpenseDTO, Long> getService() {
        return service;
    }
}
