package com.unnathy.fieldwise.leavecategory;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.leavecategory.LeaveCategoryDTO;
import com.unnathy.fieldwise.leavecategory.LeaveCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/leaveCategorys")
@RequiredArgsConstructor
public class LeaveCategoryController implements BaseController<LeaveCategoryDTO, LeaveCategoryDTO, Long> {

    private final LeaveCategoryService service;

    @Override
    public BasicEntityService<LeaveCategoryDTO, LeaveCategoryDTO, Long> getService() {
        return service;
    }
}



