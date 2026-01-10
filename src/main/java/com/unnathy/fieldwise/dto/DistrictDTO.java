package com.unnathy.fieldwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO extends BaseDTO {

    private Long stateId;
    private String name;
    private String code;
    private Boolean isActive;
}
