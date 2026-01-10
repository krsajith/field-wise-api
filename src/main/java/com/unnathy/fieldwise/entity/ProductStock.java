package com.unnathy.fieldwise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product_stock")
public class ProductStock extends BaseEntity {

    @Column(name = "product_id", nullable = false)
    private Long product;

    @Column(name = "shop_id")
    private Long shop;

    @ColumnDefault("0")
    @Column(name = "quantity", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantity;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "last_updated_at")
    private Instant lastUpdatedAt;

}
