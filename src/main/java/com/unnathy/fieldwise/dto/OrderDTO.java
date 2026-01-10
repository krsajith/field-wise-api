package com.unnathy.fieldwise.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO extends BaseDTO {

    private String orderNumber;
    private Long shopId;
    private Long userId;
    private Long shopVisitId;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String deliveryMode;
    private Long paymentModeId;
    private Long transportationModeId;
    private BigDecimal subtotal;
    private BigDecimal taxAmount;
    private BigDecimal discountAmount;
    private BigDecimal totalAmount;
    private String status;
    private Long approvedBy;
    private Instant approvedAt;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String remarks;
    private String invoicePhotoUrl;
}
