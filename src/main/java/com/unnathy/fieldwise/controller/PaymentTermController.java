package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.PaymentTermDTO;
import com.unnathy.fieldwise.service.PaymentTermService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paymentTerms")
@RequiredArgsConstructor
public class PaymentTermController implements BaseController<PaymentTermDTO, Long> {

    private final PaymentTermService service;

    @Override
    public BasicEntityService<PaymentTermDTO, Long> getService() {
        return service;
    }
}
