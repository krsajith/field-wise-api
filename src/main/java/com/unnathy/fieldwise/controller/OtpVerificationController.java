package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.OtpVerificationDTO;
import com.unnathy.fieldwise.service.OtpVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/otpVerifications")
@RequiredArgsConstructor
public class OtpVerificationController implements BaseController<OtpVerificationDTO, Long> {

    private final OtpVerificationService service;

    @Override
    public BasicEntityService<OtpVerificationDTO, Long> getService() {
        return service;
    }
}
