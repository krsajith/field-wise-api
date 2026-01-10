package com.unnathy.fieldwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShopTypeDTO extends BaseDTO {

    private String name;
    private String code;
    private String description;
    private Boolean isActive;
}
