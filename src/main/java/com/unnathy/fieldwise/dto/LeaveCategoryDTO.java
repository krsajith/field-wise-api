package com.unnathy.fieldwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveCategoryDTO extends BaseDTO {

    private String name;
    private String code;
    private Integer maxDaysPerYear;
    private Boolean requiresApproval;
    private String description;
    private Boolean isActive;
}
