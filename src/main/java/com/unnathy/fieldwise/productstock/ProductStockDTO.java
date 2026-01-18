package com.unnathy.fieldwise.productstock;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



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
