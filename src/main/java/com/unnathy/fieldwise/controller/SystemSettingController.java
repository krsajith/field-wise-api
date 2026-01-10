package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.SystemSettingDTO;
import com.unnathy.fieldwise.service.SystemSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/systemSettings")
@RequiredArgsConstructor
public class SystemSettingController implements BaseController<SystemSettingDTO, Long> {

    private final SystemSettingService service;

    @Override
    public BasicEntityService<SystemSettingDTO, Long> getService() {
        return service;
    }
}
