package com.unnathy.fieldwise.controller;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.dto.UserDTO;
import com.unnathy.fieldwise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements BaseController<UserDTO, Long> {

    private final UserService service;

    @Override
    public BasicEntityService<UserDTO, Long> getService() {
        return service;
    }
}
