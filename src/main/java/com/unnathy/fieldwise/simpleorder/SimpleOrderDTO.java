package com.unnathy.fieldwise.simpleorder;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.unnathy.fieldwise.dto.BaseDTO;



@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SimpleOrderDTO extends BaseDTO {

    private Long shopId;
    private Long userId;
    private Long shopVisitId;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String deliveryMode;
    private Long paymentModeId;
    private Long productId;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
}
