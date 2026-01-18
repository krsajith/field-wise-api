package com.unnathy.fieldwise.expense;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO extends BaseDTO {

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
