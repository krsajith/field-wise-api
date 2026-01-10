package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.ActivityDTO;
import com.unnathy.fieldwise.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activitys")
@RequiredArgsConstructor
public class ActivityController implements BaseController<ActivityDTO, Long> {

    private final ActivityService service;

    @Override
    public BasicEntityService<ActivityDTO, Long> getService() {
        return service;
    }
}
