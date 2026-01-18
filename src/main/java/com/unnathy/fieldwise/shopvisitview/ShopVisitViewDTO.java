package com.unnathy.fieldwise.shopvisitview;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.unnathy.fieldwise.dto.BaseDTO;



@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShopVisitViewDTO extends BaseDTO {

    private Long userId;
    private Long shopId;
    private Long routeId;
    private Instant checkInTime;
    private BigDecimal checkInLatitude;
    private BigDecimal checkInLongitude;
    private Instant checkOutTime;
    private BigDecimal checkOutLatitude;
    private BigDecimal checkOutLongitude;
    private Integer visitDurationMinutes;
    private String visitPurpose;
    private String notes;
    private Boolean isProductive;
    private String shopName;
    private String routeName;
}
