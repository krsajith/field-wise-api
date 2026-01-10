package com.unnathy.fieldwise.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EnquiryDTO extends BaseDTO {

    private Long userId;
    private String customerName;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    private String productInterest;
    private BigDecimal quantityEstimate;
    private BigDecimal estimatedValue;
    private LocalDate expectedClosureDate;
    private String status;
    private String priority;
    private LocalDate nextFollowUpDate;
    private String followUpNotes;
    private String lostReason;
    private Long convertedToShopId;
}
