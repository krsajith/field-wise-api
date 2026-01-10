package com.unnathy.fieldwise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "collections")
public class Collection extends BaseEntity {

    @Column(name = "collection_number", nullable = false, length = 50)
    private String collectionNumber;

    @Column(name = "shop_id", nullable = false)
    private Long shopId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "collection_date", nullable = false)
    private LocalDate collectionDate;

    @Column(name = "amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_mode", nullable = false, length = 50)
    private String paymentModeId;

    @Column(name = "cheque_number", length = 50)
    private String chequeNumber;

    @Column(name = "cheque_date")
    private LocalDate chequeDate;

    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "transaction_reference", length = 100)
    private String transactionReference;

    @ColumnDefault("'PENDING'")
    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "approved_by")
    private Long approvedById;

    @Column(name = "approved_at")
    private Instant approvedAt;

    @Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(name = "remarks", length = Integer.MAX_VALUE)
    private String remarks;

    @Column(name = "receipt_photo_url", length = 500)
    private String receiptPhotoUrl;

}
