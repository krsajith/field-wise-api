package com.unnathy.fieldwise.dto;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO extends BaseDTO {

    private Long userId;
    private Instant punchInTime;
    private Instant punchOutTime;
    private BigDecimal punchInLatitude;
    private BigDecimal punchInLongitude;
    private String punchInLocationAddress;
    private BigDecimal punchOutLatitude;
    private BigDecimal punchOutLongitude;
    private String punchOutLocationAddress;
    private Integer workDurationMinutes;
    private Boolean isVerifiedBiometric;
    private String notes;
    private String bikePhoto;
    private Integer bikeStartKm;
    private String otherNote;
    private String selectedMode;
    private String userName;
    private String profilePhotoUrl;
    private String punchStatus;
    private Instant lastActionTime;
}
