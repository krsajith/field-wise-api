package com.unnathy.fieldwise.leaveapplication;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.leaveapplication.LeaveApplicationDTO;
import com.unnathy.fieldwise.leaveapplication.LeaveApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/leaveApplications")
@RequiredArgsConstructor
public class LeaveApplicationController implements BaseController<LeaveApplicationDTO, LeaveApplicationDTO, Long> {

    private final LeaveApplicationService service;

    @Override
    public BasicEntityService<LeaveApplicationDTO, LeaveApplicationDTO, Long> getService() {
        return service;
    }
}



