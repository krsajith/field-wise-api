package com.unnathy.fieldwise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "attendance")
public class Attendance extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long user;

    @Column(name = "punch_in_time", nullable = false)
    private Instant punchInTime;

    @Column(name = "punch_out_time")
    private Instant punchOutTime;

    @Column(name = "punch_in_latitude", precision = 10, scale = 8)
    private BigDecimal punchInLatitude;

    @Column(name = "punch_in_longitude", precision = 11, scale = 8)
    private BigDecimal punchInLongitude;

    @Column(name = "punch_in_location_address", length = Integer.MAX_VALUE)
    private String punchInLocationAddress;

    @Column(name = "punch_out_latitude", precision = 10, scale = 8)
    private BigDecimal punchOutLatitude;

    @Column(name = "punch_out_longitude", precision = 11, scale = 8)
    private BigDecimal punchOutLongitude;

    @Column(name = "punch_out_location_address", length = Integer.MAX_VALUE)
    private String punchOutLocationAddress;

    @Column(name = "work_duration_minutes")
    private Integer workDurationMinutes;

    @ColumnDefault("false")
    @Column(name = "is_verified_biometric")
    private Boolean isVerifiedBiometric;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;

}
