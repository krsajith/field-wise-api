package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.UserRouteAssignmentDTO;
import com.unnathy.fieldwise.service.UserRouteAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userRouteAssignments")
@RequiredArgsConstructor
public class UserRouteAssignmentController implements BaseController<UserRouteAssignmentDTO, Long> {

    private final UserRouteAssignmentService service;

    @Override
    public BasicEntityService<UserRouteAssignmentDTO, Long> getService() {
        return service;
    }
}
