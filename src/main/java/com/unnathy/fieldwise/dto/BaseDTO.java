package com.unnathy.fieldwise.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public abstract class BaseDTO {

    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
