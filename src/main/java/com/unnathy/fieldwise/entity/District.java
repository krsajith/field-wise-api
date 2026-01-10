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
@Table(name = "districts")
public class District extends BaseEntity {

    @Column(name = "state_id", nullable = false)
    private Long stateId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "code", length = 10)
    private String code;

    @ColumnDefault("true")
    @Column(name = "is_active")
    private Boolean isActive;

}
