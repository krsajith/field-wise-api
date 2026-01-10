package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.AuditLogDTO;
import com.unnathy.fieldwise.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auditLogs")
@RequiredArgsConstructor
public class AuditLogController implements BaseController<AuditLogDTO, Long> {

    private final AuditLogService service;

    @Override
    public BasicEntityService<AuditLogDTO, Long> getService() {
        return service;
    }
}
