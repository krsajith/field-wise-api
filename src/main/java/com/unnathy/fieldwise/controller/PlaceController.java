package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.PlaceDTO;
import com.unnathy.fieldwise.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController implements BaseController<PlaceDTO, Long> {

    private final PlaceService service;

    @Override
    public BasicEntityService<PlaceDTO, Long> getService() {
        return service;
    }
}
