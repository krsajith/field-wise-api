package com.unnathy.fieldwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO extends BaseDTO {

    private String name;
    private String code;
    private Long districtId;
    private String description;
    private Boolean isActive;
}
