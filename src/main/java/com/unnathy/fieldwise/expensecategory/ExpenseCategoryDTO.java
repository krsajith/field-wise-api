package com.unnathy.fieldwise.expensecategory;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



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
