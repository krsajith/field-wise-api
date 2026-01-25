package com.unnathy.fieldwise.enquiry;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.enquiry.EnquiryDTO;
import com.unnathy.fieldwise.enquiry.EnquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/enquirys")
@RequiredArgsConstructor
public class EnquiryController implements BaseController<EnquiryDTO, EnquiryDTO, Long> {

    private final EnquiryService service;

    @Override
    public BasicEntityService<EnquiryDTO, EnquiryDTO, Long> getService() {
        return service;
    }
}



