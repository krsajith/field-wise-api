package com.unnathy.fieldwise.place;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



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
