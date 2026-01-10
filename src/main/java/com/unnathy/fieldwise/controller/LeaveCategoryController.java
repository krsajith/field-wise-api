package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.LeaveCategoryDTO;
import com.unnathy.fieldwise.service.LeaveCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leaveCategorys")
@RequiredArgsConstructor
public class LeaveCategoryController implements BaseController<LeaveCategoryDTO, Long> {

    private final LeaveCategoryService service;

    @Override
    public BasicEntityService<LeaveCategoryDTO, Long> getService() {
        return service;
    }
}
