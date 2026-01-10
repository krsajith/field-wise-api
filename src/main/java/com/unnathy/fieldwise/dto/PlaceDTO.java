package com.unnathy.fieldwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO extends BaseDTO {

    private Long districtId;
    private String name;
    private String pincode;
    private Boolean isActive;
}
