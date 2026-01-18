package com.unnathy.fieldwise.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO extends BaseDTO {

    private Long stateId;
    private String name;
    private String code;
    private Boolean isActive;
}
