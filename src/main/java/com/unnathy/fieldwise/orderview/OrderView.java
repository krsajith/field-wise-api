package com.unnathy.fieldwise.orderview;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import com.unnathy.fieldwise.entity.BaseEntity;



@Getter
@Setter
@Entity
@Immutable
@Table(name = "vw_orders")
public class OrderView extends BaseEntity {

    @Column(name = "approved_at")
    private OffsetDateTime approvedAt;

    @Column(name = "approved_by")
    private Long approvedBy;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "delivery_mode", length = 100)
    private String deliveryMode;

    @Column(name = "discount_amount", precision = 12, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "invoice_photo_url", length = 500)
    private String invoicePhotoUrl;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_number", length = 50)
    private String orderNumber;

    @Column(name = "payment_mode_id")
    private Long paymentModeId;

    @Column(name = "remarks", length = Integer.MAX_VALUE)
    private String remarks;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "shop_visit_id")
    private Long shopVisitId;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "subtotal", precision = 12, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "tax_amount", precision = 12, scale = 2)
    private BigDecimal taxAmount;

    @Column(name = "total_amount", precision = 12, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "transportation_mode_id")
    private Long transportationModeId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_total", precision = 12, scale = 2)
    private BigDecimal orderTotal;

    @Column(name = "item_count")
    private Long itemCount;

    @Column(name = "shop_name")
    private String shopName;
}
