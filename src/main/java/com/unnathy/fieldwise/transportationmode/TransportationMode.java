package com.unnathy.fieldwise.transportationmode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import com.unnathy.fieldwise.entity.BaseEntity;



@Getter
@Setter
@Entity
@Table(name = "transportation_modes")
public class TransportationMode extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ColumnDefault("true")
    @Column(name = "is_active")
    private Boolean isActive;

}
