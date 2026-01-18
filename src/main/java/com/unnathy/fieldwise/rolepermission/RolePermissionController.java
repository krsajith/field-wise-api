package com.unnathy.fieldwise.rolepermission;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.rolepermission.RolePermissionDTO;
import com.unnathy.fieldwise.rolepermission.RolePermissionService;
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
