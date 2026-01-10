package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.StateDTO;
import com.unnathy.fieldwise.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/states")
@RequiredArgsConstructor
public class StateController implements BaseController<StateDTO, Long> {

    private final StateService service;

    @Override
    public BasicEntityService<StateDTO, Long> getService() {
        return service;
    }
}
