package com.unnathy.fieldwise.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import com.unnathy.fieldwise.entity.BaseEntity;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import com.unnathy.fieldwise.entity.BaseEntity;



import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import com.unnathy.fieldwise.entity.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "order_number", nullable = false, length = 50)
    private String orderNumber;

    @Column(name = "shop_id", nullable = false)
    private Long shopId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "shop_visit_id")
    private Long shopVisitId;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "delivery_mode", length = 100)
    private String deliveryMode;

    @Column(name = "payment_mode_id")
    private Long paymentModeId;

    @Column(name = "transportation_mode_id")
    private Long transportationModeId;

    @Column(name = "subtotal", nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;

    @ColumnDefault("0")
    @Column(name = "tax_amount", precision = 12, scale = 2)
    private BigDecimal taxAmount;

    @ColumnDefault("0")
    @Column(name = "discount_amount", precision = 12, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @ColumnDefault("'PENDING'")
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "approved_by")
    private Long approvedById;

    @Column(name = "approved_at")
    private Instant approvedAt;

    @Column(name = "remarks", length = Integer.MAX_VALUE)
    private String remarks;

    @Column(name = "invoice_photo_url", length = 500)
    private String invoicePhotoUrl;

}
