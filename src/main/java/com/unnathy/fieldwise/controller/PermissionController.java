package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.PermissionDTO;
import com.unnathy.fieldwise.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController implements BaseController<PermissionDTO, Long> {

    private final PermissionService service;

    @Override
    public BasicEntityService<PermissionDTO, Long> getService() {
        return service;
    }
}
