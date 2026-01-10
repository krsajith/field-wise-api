package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.RoleDTO;
import com.unnathy.fieldwise.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController implements BaseController<RoleDTO, Long> {

    private final RoleService service;

    @Override
    public BasicEntityService<RoleDTO, Long> getService() {
        return service;
    }
}
