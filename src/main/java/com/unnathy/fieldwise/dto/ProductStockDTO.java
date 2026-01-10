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
public class ProductStockDTO extends BaseDTO {

    private Long productId;
    private Long shopId;
    private BigDecimal quantity;
    private Instant lastUpdatedAt;
}
