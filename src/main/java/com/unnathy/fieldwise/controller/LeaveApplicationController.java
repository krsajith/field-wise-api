package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.LeaveApplicationDTO;
import com.unnathy.fieldwise.service.LeaveApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leaveApplications")
@RequiredArgsConstructor
public class LeaveApplicationController implements BaseController<LeaveApplicationDTO, Long> {

    private final LeaveApplicationService service;

    @Override
    public BasicEntityService<LeaveApplicationDTO, Long> getService() {
        return service;
    }
}
