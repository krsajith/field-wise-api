package com.unnathy.fieldwise.message;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.message.MessageDTO;
import com.unnathy.fieldwise.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController implements BaseController<MessageDTO, Long> {

    private final MessageService service;

    @Override
    public BasicEntityService<MessageDTO, Long> getService() {
        return service;
    }
}
