package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.BankDTO;
import com.unnathy.fieldwise.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/banks")
@RequiredArgsConstructor
public class BankController implements BaseController<BankDTO, Long> {

    private final BankService service;

    @Override
    public BasicEntityService<BankDTO, Long> getService() {
        return service;
    }
}
