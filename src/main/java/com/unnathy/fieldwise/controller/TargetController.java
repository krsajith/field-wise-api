package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.TargetDTO;
import com.unnathy.fieldwise.service.TargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/targets")
@RequiredArgsConstructor
public class TargetController implements BaseController<TargetDTO, Long> {

    private final TargetService service;

    @Override
    public BasicEntityService<TargetDTO, Long> getService() {
        return service;
    }
}
