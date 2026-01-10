package com.unnathy.fieldwise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "sub_category_id")
    private Long subCategoryId;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "unit", length = 50)
    private String unit;

    @Column(name = "hsn_code", length = 50)
    private String hsnCode;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "mrp", precision = 10, scale = 2)
    private BigDecimal mrp;

    @Column(name = "tax_rate", precision = 5, scale = 2)
    private BigDecimal taxRate;

    @ColumnDefault("true")
    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "product_image_url", length = 500)
    private String productImageUrl;

}
