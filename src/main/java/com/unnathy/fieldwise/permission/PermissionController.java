package com.unnathy.fieldwise.permission;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.permission.PermissionDTO;
import com.unnathy.fieldwise.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController implements BaseController<PermissionDTO, PermissionDTO, Long> {

    private final PermissionService service;

    @Override
    public BasicEntityService<PermissionDTO, PermissionDTO, Long> getService() {
        return service;
    }
}



