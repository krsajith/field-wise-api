package com.unnathy.fieldwise.role;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.role.RoleDTO;
import com.unnathy.fieldwise.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController implements BaseController<RoleDTO, RoleDTO, Long> {

    private final RoleService service;

    @Override
    public BasicEntityService<RoleDTO, RoleDTO, Long> getService() {
        return service;
    }
}



