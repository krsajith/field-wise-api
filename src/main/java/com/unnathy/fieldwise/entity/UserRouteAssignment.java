package com.unnathy.fieldwise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_route_assignments")
public class UserRouteAssignment extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "route_id", nullable = false)
    private Long routeId;

    @Column(name = "assigned_date", nullable = false)
    private LocalDate assignedDate;

    @ColumnDefault("true")
    @Column(name = "is_active")
    private Boolean isActive;

}
