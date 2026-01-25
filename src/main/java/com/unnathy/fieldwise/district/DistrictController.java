package com.unnathy.fieldwise.district;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.district.DistrictDTO;
import com.unnathy.fieldwise.district.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/districts")
@RequiredArgsConstructor
public class DistrictController implements BaseController<DistrictDTO, DistrictDTO, Long> {

    private final DistrictService service;

    @Override
    public BasicEntityService<DistrictDTO, DistrictDTO, Long> getService() {
        return service;
    }
}



