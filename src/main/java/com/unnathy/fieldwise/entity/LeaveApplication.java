package com.unnathy.fieldwise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "leave_applications")
public class LeaveApplication extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long user;

    @Column(name = "category_id", nullable = false)
    private Long category;

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    @Column(name = "leave_type", nullable = false, length = 50)
    private String leaveType;

    @Column(name = "total_days", nullable = false, precision = 3, scale = 1)
    private BigDecimal totalDays;

    @Column(name = "reason", nullable = false, length = Integer.MAX_VALUE)
    private String reason;

    @ColumnDefault("'PENDING'")
    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "approved_by")
    private Long approvedBy;

    @Column(name = "approved_at")
    private Instant approvedAt;

    @Column(name = "rejection_reason", length = Integer.MAX_VALUE)
    private String rejectionReason;

    @Column(name = "attachment_url", length = 500)
    private String attachmentUrl;

}
