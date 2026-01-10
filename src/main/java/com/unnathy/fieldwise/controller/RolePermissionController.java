package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.RolePermissionDTO;
import com.unnathy.fieldwise.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rolePermissions")
@RequiredArgsConstructor
public class RolePermissionController implements BaseController<RolePermissionDTO, Long> {

    private final RolePermissionService service;

    @Override
    public BasicEntityService<RolePermissionDTO, Long> getService() {
        return service;
    }
}
