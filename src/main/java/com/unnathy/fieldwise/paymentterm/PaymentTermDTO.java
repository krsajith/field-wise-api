package com.unnathy.fieldwise.paymentterm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTermDTO extends BaseDTO {

    private String name;
    private String code;
    private Integer days;
    private String description;
    private Boolean isActive;
}
