package com.unnathy.fieldwise.userdevice;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



@Data
@EqualsAndHashCode(callSuper = true)
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
