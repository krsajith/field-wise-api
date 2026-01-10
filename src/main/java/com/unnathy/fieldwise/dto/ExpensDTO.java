package com.unnathy.fieldwise.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ExpensDTO extends BaseDTO {

    private Long userId;
    private Long categoryId;
    private LocalDate expenseDate;
    private BigDecimal amount;
    private String title;
    private String description;
    private String status;
    private Long approvedById;
    private Instant approvedAt;
    private String rejectionReason;
    private String receiptPhotoUrl;
    private String remarks;
}
