package com.unnathy.fieldwise.orderitem;

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
public class OrderItemDTO extends BaseDTO {

    private Long orderId;
    private Long productId;
    private Long shopId;
    private Long shopVisitId;
    private Long routeId;
    private BigDecimal quantity;
    private BigDecimal suppliedQuantity;
    private BigDecimal unitPrice;
    private BigDecimal taxRate;
    private BigDecimal taxAmount;
    private BigDecimal discountPercentage;
    private BigDecimal discountAmount;
    private BigDecimal lineTotal;
}
