package com.unnathy.fieldwise.dto;

import java.time.Instant;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
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
