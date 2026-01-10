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
@Table(name = "routes")
public class Route extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ColumnDefault("true")
    @Column(name = "is_active")
    private Boolean isActive;

}
