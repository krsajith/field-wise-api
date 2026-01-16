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
    private Long userId;

    @Column(name = "punch_in_time", nullable = false)
    private Instant punchInTime;

    @Column(name = "punch_out_time")
    private Instant punchOutTime;

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

    @Column(name = "bike_photo")
    private String bikePhoto;

    @Column(name = "bike_start_km")
    private Integer bikeStartKm;

    @Column(name = "other_note", length = Integer.MAX_VALUE)
    private String otherNote;

    @Column(name = "selected_mode")
    private String selectedMode;

    @Column(name = "bike_photo_out")
    private String bikePhotoOut;

    @Column(name = "bike_end_km")
    private Integer bikeEndKm;

    @Column(name = "other_note_end", length = Integer.MAX_VALUE)
    private String otherNoteEnd;

}
