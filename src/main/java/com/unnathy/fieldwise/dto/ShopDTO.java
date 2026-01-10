package com.unnathy.fieldwise.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO extends BaseDTO {

    private String name;
    private String code;
    private Long shopTypeId;
    private String contactPerson;
    private String phone;
    private String alternatePhone;
    private String email;
    private String whatsappNumber;
    private String gstNumber;
    private String panNumber;
    private String aadharNumber;
    private String addressLine1;
    private String addressLine2;
    private Long placeId;
    private Long districtId;
    private Long stateId;
    private String pincode;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer geofenceRadius;
    private Long routeId;
    private Long bankId;
    private String bankAccountNumber;
    private String bankIfscCode;
    private Long paymentTermId;
    private BigDecimal creditLimit;
    private Boolean isActive;
    private Boolean isVerified;
    private Long createdById;
    private Long verifiedById;
    private String shopPhotoUrl;
    private String notes;
}
