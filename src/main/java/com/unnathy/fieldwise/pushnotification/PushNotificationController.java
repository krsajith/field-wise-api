package com.unnathy.fieldwise.pushnotification;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.pushnotification.PushNotificationDTO;
import com.unnathy.fieldwise.pushnotification.PushNotificationService;
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
