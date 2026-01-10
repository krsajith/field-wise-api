package com.unnathy.fieldwise.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDeviceDTO extends BaseDTO {

    private Long userId;
    private String deviceToken;
    private String deviceName;
    private String deviceModel;
    private String osName;
    private String osVersion;
    private String appVersion;
    private String fcmToken;
    private Boolean isActive;
    private Instant firstRegisteredAt;
    private Instant lastSeenAt;
}
