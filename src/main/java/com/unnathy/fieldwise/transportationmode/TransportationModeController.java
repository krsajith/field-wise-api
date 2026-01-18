package com.unnathy.fieldwise.transportationmode;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.transportationmode.TransportationModeDTO;
import com.unnathy.fieldwise.transportationmode.TransportationModeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/transportationModes")
@RequiredArgsConstructor
public class TransportationModeController implements BaseController<TransportationModeDTO, Long> {

    private final TransportationModeService service;

    @Override
    public BasicEntityService<TransportationModeDTO, Long> getService() {
        return service;
    }
}
