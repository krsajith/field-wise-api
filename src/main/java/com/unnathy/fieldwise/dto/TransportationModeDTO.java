package com.unnathy.fieldwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportationModeDTO extends BaseDTO {

    private String name;
    private String code;
    private String description;
    private Boolean isActive;
}
