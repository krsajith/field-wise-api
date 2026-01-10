package com.unnathy.fieldwise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "feedback")
public class Feedback extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "feedback_type", nullable = false, length = 50)
    private String feedbackType;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "rating")
    private Integer rating;

    @ColumnDefault("'OPEN'")
    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "attachment_url", length = 500)
    private String attachmentUrl;

}
