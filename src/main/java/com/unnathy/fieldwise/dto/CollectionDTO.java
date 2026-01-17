package com.unnathy.fieldwise.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDTO extends BaseDTO {

    private String collectionNumber;
    private Long shopId;
    private Long userId;
    private Long orderId;
    private LocalDate collectionDate;
    private BigDecimal amount;
    private String paymentMode;
    private String chequeNumber;
    private LocalDate chequeDate;
    private Long bankId;
    private String transactionReference;
    private String status;
    private Long approvedById;
    private Instant approvedAt;
    private String remarks;
    private String receiptPhotoUrl;
}
