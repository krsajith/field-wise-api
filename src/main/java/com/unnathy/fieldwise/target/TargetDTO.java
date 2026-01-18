package com.unnathy.fieldwise.target;

import java.math.BigDecimal;
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
