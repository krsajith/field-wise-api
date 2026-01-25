package com.unnathy.fieldwise.user;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.user.UserDTO;
import com.unnathy.fieldwise.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements BaseController<UserDTO, UserDTO, Long> {

    private final UserService service;

    @Override
    public BasicEntityService<UserDTO, UserDTO, Long> getService() {
        return service;
    }
}



