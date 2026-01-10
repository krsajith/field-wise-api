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
@Table(name = "enquiries")
public class Enquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address", length = Integer.MAX_VALUE)
    private String address;

    @Column(name = "product_interest", length = Integer.MAX_VALUE)
    private String productInterest;

    @Column(name = "quantity_estimate", precision = 10, scale = 2)
    private BigDecimal quantityEstimate;

    @Column(name = "estimated_value", precision = 12, scale = 2)
    private BigDecimal estimatedValue;

    @Column(name = "expected_closure_date")
    private LocalDate expectedClosureDate;

    @ColumnDefault("'NEW'")
    @Column(name = "status", length = 50)
    private String status;

    @ColumnDefault("'MEDIUM'")
    @Column(name = "priority", length = 50)
    private String priority;

    @Column(name = "next_follow_up_date")
    private LocalDate nextFollowUpDate;

    @Column(name = "follow_up_notes", length = Integer.MAX_VALUE)
    private String followUpNotes;

    @Column(name = "lost_reason", length = Integer.MAX_VALUE)
    private String lostReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "converted_to_shop_id")
    private Shop convertedToShop;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;


}