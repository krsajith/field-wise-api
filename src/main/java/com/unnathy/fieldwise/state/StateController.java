package com.unnathy.fieldwise.state;

import com.unnathy.fieldwise.core.BaseController;
import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.state.StateDTO;
import com.unnathy.fieldwise.state.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/states")
@RequiredArgsConstructor
public class StateController implements BaseController<StateDTO, StateDTO, Long> {

    private final StateService service;

    @Override
    public BasicEntityService<StateDTO, StateDTO, Long> getService() {
        return service;
    }
}



