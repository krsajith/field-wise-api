package com.unnathy.fieldwise.product;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO extends BaseDTO {

    private String name;
    private String code;
    private Long categoryId;
    private Long subCategoryId;
    private String description;
    private String unit;
    private String hsnCode;
    private BigDecimal price;
    private BigDecimal mrp;
    private BigDecimal taxRate;
    private Boolean isActive;
    private String productImageUrl;
}
