package com.unnathy.fieldwise.target;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.target.TargetDTO;
import com.unnathy.fieldwise.target.TargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/targets")
@RequiredArgsConstructor
public class TargetController implements BaseController<TargetDTO, TargetDTO, Long> {

    private final TargetService service;

    @Override
    public BasicEntityService<TargetDTO, TargetDTO, Long> getService() {
        return service;
    }
}



