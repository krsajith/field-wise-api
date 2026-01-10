package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.ComplaintDTO;
import com.unnathy.fieldwise.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController implements BaseController<ComplaintDTO, Long> {

    private final ComplaintService service;

    @Override
    public BasicEntityService<ComplaintDTO, Long> getService() {
        return service;
    }
}
