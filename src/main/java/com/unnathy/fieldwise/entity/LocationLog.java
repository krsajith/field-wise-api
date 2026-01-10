package com.unnathy.fieldwise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "location_logs")
public class LocationLog extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "latitude", nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(name = "accuracy", precision = 8, scale = 2)
    private BigDecimal accuracy;

    @Column(name = "speed", precision = 8, scale = 2)
    private BigDecimal speed;

    @Column(name = "battery_level")
    private Integer batteryLevel;

    @Column(name = "\"timestamp\"", nullable = false)
    private Instant timestamp;

}
