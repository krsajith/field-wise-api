package com.unnathy.fieldwise.userrouteassignment;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.userrouteassignment.UserRouteAssignmentDTO;
import com.unnathy.fieldwise.userrouteassignment.UserRouteAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/userRouteAssignments")
@RequiredArgsConstructor
public class UserRouteAssignmentController implements BaseController<UserRouteAssignmentDTO, UserRouteAssignmentDTO, Long> {

    private final UserRouteAssignmentService service;

    @Override
    public BasicEntityService<UserRouteAssignmentDTO, UserRouteAssignmentDTO, Long> getService() {
        return service;
    }
}



