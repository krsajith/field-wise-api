package com.unnathy.fieldwise.dto;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShopVisitDTO extends BaseDTO {

    private Long userId;
    private Long shopId;
    private Long routeId;
    private Instant checkInTime;
    private Instant checkOutTime;
    private Integer visitDurationMinutes;
    private String visitPurpose;
    private String notes;
    private Boolean isProductive;
}
