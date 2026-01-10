package com.unnathy.fieldwise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "activities")
public class Activity extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "activity_type", nullable = false, length = 50)
    private String activityType;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "scheduled_date")
    private LocalDate scheduledDate;

    @Column(name = "scheduled_time")
    private LocalTime scheduledTime;

    @Column(name = "location")
    private String location;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "contact_phone", length = 20)
    private String contactPhone;

    @Column(name = "contact_email")
    private String contactEmail;

    @ColumnDefault("'SCHEDULED'")
    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "outcome", length = Integer.MAX_VALUE)
    private String outcome;

}
