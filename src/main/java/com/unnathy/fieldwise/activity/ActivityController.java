package com.unnathy.fieldwise.activity;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.activity.ActivityDTO;
import com.unnathy.fieldwise.activity.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/activitys")
@RequiredArgsConstructor
public class ActivityController implements BaseController<ActivityDTO, ActivityDTO, Long> {

    private final ActivityService service;

    @Override
    public BasicEntityService<ActivityDTO, ActivityDTO, Long> getService() {
        return service;
    }
}



