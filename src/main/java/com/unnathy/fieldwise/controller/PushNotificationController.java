package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.PushNotificationDTO;
import com.unnathy.fieldwise.service.PushNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pushNotifications")
@RequiredArgsConstructor
public class PushNotificationController implements BaseController<PushNotificationDTO, Long> {

    private final PushNotificationService service;

    @Override
    public BasicEntityService<PushNotificationDTO, Long> getService() {
        return service;
    }
}
