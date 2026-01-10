package com.unnathy.fieldwise.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCategoryDTO extends BaseDTO {

    private String name;
    private String code;
    private BigDecimal maxAmount;
    private Boolean requiresApproval;
    private String description;
    private Boolean isActive;
}
