package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.DistrictDTO;
import com.unnathy.fieldwise.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/districts")
@RequiredArgsConstructor
public class DistrictController implements BaseController<DistrictDTO, Long> {

    private final DistrictService service;

    @Override
    public BasicEntityService<DistrictDTO, Long> getService() {
        return service;
    }
}
