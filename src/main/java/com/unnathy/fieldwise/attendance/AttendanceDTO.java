package com.unnathy.fieldwise.attendance;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;
import org.hibernate.annotations.ColumnDefault;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO extends BaseDTO {

    private Long userId;
    private Instant punchInTime;
    private Instant punchOutTime;
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
    private String bikePhotoOut;
    private Integer bikeEndKm;
    private String otherNoteEnd;
}
