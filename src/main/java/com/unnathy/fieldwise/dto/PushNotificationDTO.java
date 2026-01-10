package com.unnathy.fieldwise.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushNotificationDTO extends BaseDTO {

    private Long userId;
    private String title;
    private String body;
    private String notificationType;
    private Map<String, Object> data;
    private Boolean isSent;
    private Instant sentAt;
}
