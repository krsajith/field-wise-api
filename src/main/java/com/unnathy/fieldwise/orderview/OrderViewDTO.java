package com.unnathy.fieldwise.orderview;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.unnathy.fieldwise.dto.BaseDTO;



@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderViewDTO extends BaseDTO {
    private OffsetDateTime approvedAt;
    private Long approvedBy;
    private LocalDate deliveryDate;
    private String deliveryMode;
    private BigDecimal discountAmount;
    private String invoicePhotoUrl;
    private LocalDate orderDate;
    private String orderNumber;
    private Long paymentModeId;
    private String remarks;
    private Long shopId;
    private Long shopVisitId;
    private String status;
    private BigDecimal subtotal;
    private BigDecimal taxAmount;
    private BigDecimal totalAmount;
    private Long transportationModeId;
    private Long userId;
    private BigDecimal orderTotal;
    private Long itemCount;
    private String shopName;
}
