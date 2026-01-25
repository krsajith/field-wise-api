package com.unnathy.fieldwise.userdevice;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.userdevice.UserDeviceDTO;
import com.unnathy.fieldwise.userdevice.UserDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/userDevices")
@RequiredArgsConstructor
public class UserDeviceController implements BaseController<UserDeviceDTO, UserDeviceDTO, Long> {

    private final UserDeviceService service;

    @Override
    public BasicEntityService<UserDeviceDTO, UserDeviceDTO, Long> getService() {
        return service;
    }
}



