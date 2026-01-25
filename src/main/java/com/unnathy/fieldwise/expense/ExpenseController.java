package com.unnathy.fieldwise.expense;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.expense.ExpenseDTO;
import com.unnathy.fieldwise.expense.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController implements BaseController<ExpenseDTO, VwExpenseDTO, Long> {

    private final ExpenseService service;

    @Override
    public BasicEntityService<ExpenseDTO, VwExpenseDTO, Long> getService() {
        return service;
    }
}



