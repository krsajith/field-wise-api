package com.unnathy.fieldwise.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO extends BaseDTO {

    private String name;
    private String code;
    private Long districtId;
    private String description;
    private Boolean isActive;
}
