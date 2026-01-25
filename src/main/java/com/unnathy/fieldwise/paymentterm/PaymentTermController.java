package com.unnathy.fieldwise.paymentterm;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.paymentterm.PaymentTermDTO;
import com.unnathy.fieldwise.paymentterm.PaymentTermService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/paymentTerms")
@RequiredArgsConstructor
public class PaymentTermController implements BaseController<PaymentTermDTO, PaymentTermDTO, Long> {

    private final PaymentTermService service;

    @Override
    public BasicEntityService<PaymentTermDTO, PaymentTermDTO, Long> getService() {
        return service;
    }
}



