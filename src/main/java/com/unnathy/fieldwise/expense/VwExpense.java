package com.unnathy.fieldwise.expense;

import com.unnathy.fieldwise.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Entity
@Immutable
@Table(name = "vw_expenses", schema = "field-wise")
public class VwExpense extends BaseEntity {

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "approved_at")
    private OffsetDateTime approvedAt;

    @Column(name = "approved_by")
    private Long approvedBy;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "expense_date")
    private LocalDate expenseDate;

    @Size(max = 500)
    @Column(name = "receipt_photo_url", length = 500)
    private String receiptPhotoUrl;

    @Column(name = "rejection_reason", length = Integer.MAX_VALUE)
    private String rejectionReason;

    @Column(name = "remarks", length = Integer.MAX_VALUE)
    private String remarks;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

    @Size(max = 255)
    @Column(name = "title")
    private String title;

    @Column(name = "user_id")
    private Long userId;

    @Size(max = 100)
    @Column(name = "expense_category", length = 100)
    private String expenseCategory;

    @Size(max = 50)
    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "requires_approval")
    private Boolean requiresApproval;


}