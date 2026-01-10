package com.unnathy.fieldwise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Entity
@Immutable
@Table(name = "v_pending_approvals")
public class VPendingApproval {
    @Column(name = "approval_type", length = Integer.MAX_VALUE)
    private String approvalType;

    @Column(name = "pending_count")
    private Long pendingCount;


}