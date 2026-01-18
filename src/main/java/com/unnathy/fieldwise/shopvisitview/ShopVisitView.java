package com.unnathy.fieldwise.shopvisitview;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import java.time.Instant;
import com.unnathy.fieldwise.entity.BaseEntity;
import java.time.Instant;
import com.unnathy.fieldwise.entity.BaseEntity;



import java.math.BigDecimal;
import java.time.Instant;
import com.unnathy.fieldwise.entity.BaseEntity;

@Getter
@Setter
@Entity
@Immutable
@Table(name = "vw_shop_visit")
public class ShopVisitView extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "check_in_time")
    private Instant checkInTime;

    @Column(name = "check_out_time")
    private Instant checkOutTime;

    @Column(name = "visit_duration_minutes")
    private Integer visitDurationMinutes;

    @Column(name = "visit_purpose", length = 100)
    private String visitPurpose;

    @Column(name = "notes", length = Integer.MAX_VALUE)
    private String notes;

    @Column(name = "is_productive")
    private Boolean isProductive;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "route_name")
    private String routeName;
}
