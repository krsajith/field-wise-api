package com.unnathy.fieldwise.dto;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
}
