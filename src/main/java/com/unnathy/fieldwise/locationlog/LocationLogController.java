package com.unnathy.fieldwise.locationlog;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.locationlog.LocationLogDTO;
import com.unnathy.fieldwise.locationlog.LocationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/locationLogs")
@RequiredArgsConstructor
public class LocationLogController implements BaseController<LocationLogDTO, LocationLogDTO, Long> {

    private final LocationLogService service;

    @Override
    public BasicEntityService<LocationLogDTO, LocationLogDTO, Long> getService() {
        return service;
    }
}



