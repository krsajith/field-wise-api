package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.EnquiryDTO;
import com.unnathy.fieldwise.service.EnquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enquirys")
@RequiredArgsConstructor
public class EnquiryController implements BaseController<EnquiryDTO, Long> {

    private final EnquiryService service;

    @Override
    public BasicEntityService<EnquiryDTO, Long> getService() {
        return service;
    }
}
