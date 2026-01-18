package com.unnathy.fieldwise.role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import com.unnathy.fieldwise.entity.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import com.unnathy.fieldwise.entity.BaseEntity;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import com.unnathy.fieldwise.entity.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Getter
    @Entity
    @Immutable
    @Table(name = "vw_orders", schema = "field-wise")
    public static class VwOrder {

        @Id
        @Column(name = "id")
        private Long id;

        @Column(name = "created_at")
        private OffsetDateTime createdAt;

        @Column(name = "latitude", precision = 10, scale = 8)
        private BigDecimal latitude;

        @Column(name = "longitude", precision = 11, scale = 8)
        private BigDecimal longitude;

        @Column(name = "updated_at")
        private OffsetDateTime updatedAt;

        @Column(name = "approved_at")
        private OffsetDateTime approvedAt;

        @Column(name = "approved_by")
        private Long approvedBy;

        @Column(name = "delivery_date")
        private LocalDate deliveryDate;

        @Size(max = 100)
        @Column(name = "delivery_mode", length = 100)
        private String deliveryMode;

        @Column(name = "discount_amount", precision = 12, scale = 2)
        private BigDecimal discountAmount;

        @Size(max = 500)
        @Column(name = "invoice_photo_url", length = 500)
        private String invoicePhotoUrl;

        @Column(name = "order_date")
        private LocalDate orderDate;

        @Size(max = 50)
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

        @Size(max = 50)
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

        @Column(name = "created_by")
        private Long createdBy;

        @Column(name = "updated_by")
        private Long updatedBy;

        @Column(name = "order_total")
        private BigDecimal orderTotal;

        @Column(name = "item_count")
        private Long itemCount;

        @Size(max = 255)
        @Column(name = "shop_name")
        private String shopName;


    }
}
