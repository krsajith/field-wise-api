package com.unnathy.fieldwise.auditlog;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.auditlog.AuditLogDTO;
import com.unnathy.fieldwise.auditlog.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auditLogs")
@RequiredArgsConstructor
public class AuditLogController implements BaseController<AuditLogDTO, AuditLogDTO, Long> {

    private final AuditLogService service;

    @Override
    public BasicEntityService<AuditLogDTO, AuditLogDTO, Long> getService() {
        return service;
    }
}



