package com.unnathy.fieldwise.state;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class StateDTO extends BaseDTO {

    private String name;
    private String code;
    private Boolean isActive;
}
