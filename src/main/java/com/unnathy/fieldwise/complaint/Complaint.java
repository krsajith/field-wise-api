package com.unnathy.fieldwise.complaint;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import java.time.Instant;
import com.unnathy.fieldwise.entity.BaseEntity;
import java.time.Instant;
import com.unnathy.fieldwise.entity.BaseEntity;



import java.time.Instant;
import com.unnathy.fieldwise.entity.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "complaints")
public class Complaint extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "complaint_type", nullable = false, length = 50)
    private String complaintType;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @ColumnDefault("'MEDIUM'")
    @Column(name = "priority", length = 50)
    private String priority;

    @ColumnDefault("'OPEN'")
    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "assigned_to")
    private Long assignedToId;

    @Column(name = "resolved_at")
    private Instant resolvedAt;

    @Column(name = "resolution_notes", length = Integer.MAX_VALUE)
    private String resolutionNotes;

    @Column(name = "attachment_url", length = 500)
    private String attachmentUrl;

}
