package com.unnathy.fieldwise.simpleorderview;

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
public class SimpleOrderViewDTO extends BaseDTO {

    private Long userId;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private LocalDate orderDate;
    private String product;
    private String shopName;
}
