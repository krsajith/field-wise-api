package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.LocationLogDTO;
import com.unnathy.fieldwise.service.LocationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locationLogs")
@RequiredArgsConstructor
public class LocationLogController implements BaseController<LocationLogDTO, Long> {

    private final LocationLogService service;

    @Override
    public BasicEntityService<LocationLogDTO, Long> getService() {
        return service;
    }
}
