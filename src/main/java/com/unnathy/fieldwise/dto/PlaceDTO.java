package com.unnathy.fieldwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO extends BaseDTO {

    private Long districtId;
    private String name;
    private String pincode;
    private Boolean isActive;
}
