package com.unnathy.fieldwise.simpleorderview;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;



@Getter
@Setter
@Entity
@Immutable
@Table(name = "v_orders")
public class SimpleOrderView {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "quantity", precision = 10, scale = 2)
    private BigDecimal quantity;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "product")
    private String product;

    @Column(name = "shop_name")
    private String shopName;
}
