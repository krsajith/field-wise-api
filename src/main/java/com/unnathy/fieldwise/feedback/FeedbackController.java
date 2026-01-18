package com.unnathy.fieldwise.feedback;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.feedback.FeedbackDTO;
import com.unnathy.fieldwise.feedback.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController implements BaseController<FeedbackDTO, Long> {

    private final FeedbackService service;

    @Override
    public BasicEntityService<FeedbackDTO, Long> getService() {
        return service;
    }
}
