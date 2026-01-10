package com.unnathy.fieldwise.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class LeaveApplicationDTO extends BaseDTO {

    private Long userId;
    private Long categoryId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String leaveType;
    private BigDecimal totalDays;
    private String reason;
    private String status;
    private Long approvedById;
    private Instant approvedAt;
    private String rejectionReason;
    private String attachmentUrl;
}
