package com.unnathy.fieldwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SystemSettingDTO extends BaseDTO {

    private String settingKey;
    private String settingValue;
    private String dataType;
    private String description;
    private Boolean isPublic;
    private Long updatedById;
}
