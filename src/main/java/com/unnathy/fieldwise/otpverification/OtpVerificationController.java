package com.unnathy.fieldwise.otpverification;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.otpverification.OtpVerificationDTO;
import com.unnathy.fieldwise.otpverification.OtpVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/otpVerifications")
@RequiredArgsConstructor
public class OtpVerificationController implements BaseController<OtpVerificationDTO, OtpVerificationDTO, Long> {

    private final OtpVerificationService service;

    @Override
    public BasicEntityService<OtpVerificationDTO, OtpVerificationDTO, Long> getService() {
        return service;
    }
}



