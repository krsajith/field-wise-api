package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.MessageDTO;
import com.unnathy.fieldwise.service.MessageService;
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
