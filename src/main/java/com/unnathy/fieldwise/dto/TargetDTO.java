package com.unnathy.fieldwise.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TargetDTO extends BaseDTO {

    private Long userId;
    private String targetType;
    private String periodType;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal targetValue;
    private BigDecimal achievedValue;
    private BigDecimal achievementPercentage;
    private Long createdById;
}
