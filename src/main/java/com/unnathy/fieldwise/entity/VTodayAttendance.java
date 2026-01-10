package com.unnathy.fieldwise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.time.Instant;

@Getter
@Entity
@Immutable
@Table(name = "v_today_attendance")
public class VTodayAttendance {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", length = Integer.MAX_VALUE)
    private String userName;

    @Column(name = "punch_in_time")
    private Instant punchInTime;

    @Column(name = "punch_out_time")
    private Instant punchOutTime;

    @Column(name = "work_duration_minutes")
    private Integer workDurationMinutes;

    @Column(name = "punch_in_location_address", length = Integer.MAX_VALUE)
    private String punchInLocationAddress;


}