package com.unnathy.fieldwise.expense;

import com.unnathy.fieldwise.dto.BaseDTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class VwExpenseDTO extends ExpenseDTO {
    private String expenseCategory;
    private String code;
    private Boolean requiresApproval;
}
