package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.CollectionDTO;
import com.unnathy.fieldwise.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/collections")
@RequiredArgsConstructor
public class CollectionController implements BaseController<CollectionDTO, Long> {

    private final CollectionService service;

    @Override
    public BasicEntityService<CollectionDTO, Long> getService() {
        return service;
    }
}
