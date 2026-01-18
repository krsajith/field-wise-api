package com.unnathy.fieldwise.target;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.unnathy.fieldwise.entity.BaseEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.unnathy.fieldwise.entity.BaseEntity;



import java.math.BigDecimal;
import java.time.LocalDate;
import com.unnathy.fieldwise.entity.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "targets")
public class Target extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "target_type", nullable = false, length = 50)
    private String targetType;

    @Column(name = "period_type", nullable = false, length = 50)
    private String periodType;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "target_value", nullable = false, precision = 12, scale = 2)
    private BigDecimal targetValue;

    @ColumnDefault("0")
    @Column(name = "achieved_value", precision = 12, scale = 2)
    private BigDecimal achievedValue;

    @ColumnDefault("0")
    @Column(name = "achievement_percentage", precision = 5, scale = 2)
    private BigDecimal achievementPercentage;


}
