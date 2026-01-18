package com.unnathy.fieldwise.auditlog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;
import java.util.Map;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;



import java.util.Map;
import lombok.EqualsAndHashCode;
import com.unnathy.fieldwise.dto.BaseDTO;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogDTO extends BaseDTO {

    private Long userId;
    private String tableName;
    private Long recordId;
    private String action;
    private Map<String, Object> oldValues;
    private Map<String, Object> newValues;
    private String ipAddress;
    private String userAgent;
}
