package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.AttendanceDTO;
import com.unnathy.fieldwise.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
public class AttendanceController implements BaseController<AttendanceDTO, Long> {

    private final AttendanceService service;

    @Override
    public BasicEntityService<AttendanceDTO, Long> getService() {
        return service;
    }
}
