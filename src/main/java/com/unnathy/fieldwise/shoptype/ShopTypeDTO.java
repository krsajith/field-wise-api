package com.unnathy.fieldwise.shoptype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



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
