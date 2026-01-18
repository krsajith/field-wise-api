package com.unnathy.fieldwise.systemsetting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



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
