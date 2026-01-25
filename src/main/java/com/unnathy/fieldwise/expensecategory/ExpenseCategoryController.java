package com.unnathy.fieldwise.expensecategory;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.expensecategory.ExpenseCategoryDTO;
import com.unnathy.fieldwise.expensecategory.ExpenseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/expenseCategorys")
@RequiredArgsConstructor
public class ExpenseCategoryController implements BaseController<ExpenseCategoryDTO, ExpenseCategoryDTO, Long> {

    private final ExpenseCategoryService service;

    @Override
    public BasicEntityService<ExpenseCategoryDTO, ExpenseCategoryDTO, Long> getService() {
        return service;
    }
}



